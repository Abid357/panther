package AppLogic;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import Core.Item;
import Core.Salary;

public class Salaries {
	private static ArrayList<Salary> salaries = new ArrayList<Salary>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<Salary> list) {
		salaries = list;
	}

	public static ArrayList<Salary> getList() {
		return salaries;
	}

	public static void deleteList() {
		salaries = new ArrayList<Salary>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.SalaryDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.SalaryDatabase.save();
	}

	public static boolean add(Salary salary) {
		if (indexOf(salary.getNumber()) == -1) {
			if (AppLogic.Transactions.add(salary))
				return salaries.add(salary);
			else
				return false;
		} else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= salaries.size() || index == -1)
			return false;
		else
			return (salaries.remove(index) != null);
	}

	public static boolean update(int number, Date date, Time time, double amount, char type, String description,
			String bankAccountNo, String chequeNo, String entityName, ArrayList<Item> items) {
		int index = indexOf(number);
		if (index != -1) {
			if (date != null)
				salaries.get(index).setDate(date);
			if (time != null)
				salaries.get(index).setTime(time);
			salaries.get(index).setAmount(amount);
			salaries.get(index).setType(type);
			if (description != null)
				salaries.get(index).setDescription(description);
			if (bankAccountNo != null)
				salaries.get(index).setBankAccountNo(bankAccountNo);
			if (chequeNo != null)
				salaries.get(index).setChequeNo(chequeNo);
			if (entityName != null)
				salaries.get(index).setEntityName(entityName);
			if (items != null)
				salaries.get(index).setItems(items);
			return true;
		}
		return false;
	}

	public static int indexOf(int number) {
		for (int i = 0; i < salaries.size(); i++) {
			if (salaries.get(i).getNumber() == number)
				return i;
		}
		return -1;
	}

	public static Salary get(int index) {
		if (index >= salaries.size() || index == -1)
			return null;
		else
			return salaries.get(index);
	}
}
