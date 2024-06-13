package AppLogic;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import Core.Investment;
import Core.Item;

public class Investments {
	private static ArrayList<Investment> investments = new ArrayList<Investment>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<Investment> list) {
		investments = list;
	}

	public static ArrayList<Investment> getList() {
		return investments;
	}

	public static void deleteList() {
		investments = new ArrayList<Investment>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.InvestmentDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.InvestmentDatabase.save();
	}

	public static boolean add(Investment investment) {
		if (indexOf(investment.getNumber()) == -1) {
			if (AppLogic.Transactions.add(investment))
				return investments.add(investment);
			else
				return false;
		} else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= investments.size() || index == -1)
			return false;
		else
			return (investments.remove(index) != null);
	}

	public static boolean update(int number, Date date, Time time, double amount, char type, String description,
			String bankAccountNo, String chequeNo, String entityName, ArrayList<Item> items) {
		int index = indexOf(number);
		if (index != -1) {
			if (date != null)
				investments.get(index).setDate(date);
			if (time != null)
				investments.get(index).setTime(time);
			investments.get(index).setAmount(amount);
			investments.get(index).setType(type);
			if (description != null)
				investments.get(index).setDescription(description);
			if (bankAccountNo != null)
				investments.get(index).setBankAccountNo(bankAccountNo);
			if (chequeNo != null)
				investments.get(index).setChequeNo(chequeNo);
			if (entityName != null)
				investments.get(index).setEntityName(entityName);
			if (items != null)
				investments.get(index).setItems(items);
			return true;
		}
		return false;
	}

	public static int indexOf(int number) {
		for (int i = 0; i < investments.size(); i++) {
			if (investments.get(i).getNumber() == number)
				return i;
		}
		return -1;
	}

	public static Investment get(int index) {
		if (index >= investments.size() || index == -1)
			return null;
		else
			return investments.get(index);
	}
}
