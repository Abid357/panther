package AppLogic;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import Core.Item;
import Core.Profit;

public class Profits {
	private static ArrayList<Profit> profits = new ArrayList<Profit>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<Profit> list) {
		profits = list;
	}

	public static ArrayList<Profit> getList() {
		return profits;
	}

	public static void deleteList() {
		profits = new ArrayList<Profit>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.ProfitDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.ProfitDatabase.save();
	}

	public static boolean add(Profit profit) {
		if (indexOf(profit.getNumber()) == -1) {
			if (AppLogic.Transactions.add(profit))
				return profits.add(profit);
			else
				return false;
		} else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= profits.size() || index == -1)
			return false;
		else
			return (profits.remove(index) != null);
	}

	public static boolean update(int number, Date date, Time time, double amount, char type, String description,
			String bankAccountNo, String chequeNo, String entityName, ArrayList<Item> items) {
		int index = indexOf(number);
		if (index != -1) {
			if (date != null)
				profits.get(index).setDate(date);
			if (time != null)
				profits.get(index).setTime(time);
			profits.get(index).setAmount(amount);
			profits.get(index).setType(type);
			if (description != null)
				profits.get(index).setDescription(description);
			if (bankAccountNo != null)
				profits.get(index).setBankAccountNo(bankAccountNo);
			if (chequeNo != null)
				profits.get(index).setChequeNo(chequeNo);
			if (entityName != null)
				profits.get(index).setEntityName(entityName);
			if (items != null)
				profits.get(index).setItems(items);
			return true;
		}
		return false;
	}

	public static int indexOf(int number) {
		for (int i = 0; i < profits.size(); i++) {
			if (profits.get(i).getNumber() == number)
				return i;
		}
		return -1;
	}

	public static Profit get(int index) {
		if (index >= profits.size() || index == -1)
			return null;
		else
			return profits.get(index);
	}
}
