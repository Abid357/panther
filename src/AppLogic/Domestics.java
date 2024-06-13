package AppLogic;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import Core.Domestic;
import Core.Item;

public class Domestics {
	private static ArrayList<Domestic> domestics = new ArrayList<Domestic>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<Domestic> list) {
		domestics = list;
	}

	public static ArrayList<Domestic> getList() {
		return domestics;
	}

	public static void deleteList() {
		domestics = new ArrayList<Domestic>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.DomesticDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.DomesticDatabase.save();
	}

	public static boolean add(Domestic domestic) {
		if (indexOf(domestic.getNumber()) == -1) {
			if (AppLogic.Transactions.add(domestic))
				return domestics.add(domestic);
			else
				return false;
		} else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= domestics.size() || index == -1)
			return false;
		else
			return (domestics.remove(index) != null);
	}

	public static boolean update(int number, Date date, Time time, double amount, char type, String description,
			String bankAccountNo, String chequeNo, String entityName, ArrayList<Item> items) {
		int index = indexOf(number);
		if (index != -1) {
			if (date != null)
				domestics.get(index).setDate(date);
			if (time != null)
				domestics.get(index).setTime(time);
			domestics.get(index).setAmount(amount);
			domestics.get(index).setType(type);
			if (description != null)
				domestics.get(index).setDescription(description);
			if (bankAccountNo != null)
				domestics.get(index).setBankAccountNo(bankAccountNo);
			if (chequeNo != null)
				domestics.get(index).setChequeNo(chequeNo);
			if (entityName != null)
				domestics.get(index).setEntityName(entityName);
			if (items != null)
				domestics.get(index).setItems(items);
			return true;
		}
		return false;
	}

	public static int indexOf(int number) {
		for (int i = 0; i < domestics.size(); i++) {
			if (domestics.get(i).getNumber() == number)
				return i;
		}
		return -1;
	}

	public static Domestic get(int index) {
		if (index >= domestics.size() || index == -1)
			return null;
		else
			return domestics.get(index);
	}
}
