package AppLogic;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import Core.Item;
import Core.Manufacture;

public class Manufactures {
	private static ArrayList<Manufacture> manufactures = new ArrayList<Manufacture>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<Manufacture> list) {
		manufactures = list;
	}

	public static ArrayList<Manufacture> getList() {
		return manufactures;
	}

	public static void deleteList() {
		manufactures = new ArrayList<Manufacture>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.ManufactureDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.ManufactureDatabase.save();
	}

	public static boolean add(Manufacture manufacture) {
		if (indexOf(manufacture.getNumber()) == -1) {
			if (AppLogic.Transactions.add(manufacture))
				return manufactures.add(manufacture);
			else
				return false;
		} else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= manufactures.size() || index == -1)
			return false;
		else
			return (manufactures.remove(index) != null);
	}

	public static boolean update(int number, Date date, Time time, double amount, char type, String description,
			String bankAccountNo, String chequeNo, String entityName, ArrayList<Item> items, int inventoryNo) {
		int index = indexOf(number);
		if (index != -1) {
			if (date != null)
				manufactures.get(index).setDate(date);
			if (time != null)
				manufactures.get(index).setTime(time);
			manufactures.get(index).setAmount(amount);
			manufactures.get(index).setType(type);
			if (description != null)
				manufactures.get(index).setDescription(description);
			if (bankAccountNo != null)
				manufactures.get(index).setBankAccountNo(bankAccountNo);
			if (chequeNo != null)
				manufactures.get(index).setChequeNo(chequeNo);
			if (entityName != null)
				manufactures.get(index).setEntityName(entityName);
			if (items != null)
				manufactures.get(index).setItems(items);
			manufactures.get(index).setInventoryNo(inventoryNo);
			return true;
		}
		return false;
	}

	public static int indexOf(int number) {
		for (int i = 0; i < manufactures.size(); i++) {
			if (manufactures.get(i).getNumber() == number)
				return i;
		}
		return -1;
	}

	public static Manufacture get(int index) {
		if (index >= manufactures.size() || index == -1)
			return null;
		else
			return manufactures.get(index);
	}
}
