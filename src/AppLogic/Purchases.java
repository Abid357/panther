package AppLogic;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import Core.Purchase;
import Core.Item;

public class Purchases {
	private static ArrayList<Purchase> imports = new ArrayList<Purchase>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<Purchase> list) {
		imports = list;
	}

	public static ArrayList<Purchase> getList() {
		return imports;
	}

	public static void deleteList() {
		imports = new ArrayList<Purchase>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.PurchaseDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.PurchaseDatabase.save();
	}

	public static boolean add(Purchase imp) {
		if (indexOf(imp.getNumber()) == -1) {
			if (AppLogic.Transactions.add(imp))
				return imports.add(imp);
			else
				return false;
		} else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= imports.size() || index == -1)
			return false;
		else
			return (imports.remove(index) != null);
	}

	public static boolean update(int number, Date date, Time time, double amount, char type, String description,
			String bankAccountNo, String chequeNo, String entityName, ArrayList<Item> items, int transportTransactionNo) {
		int index = indexOf(number);
		if (index != -1) {
			if (date != null)
				imports.get(index).setDate(date);
			if (time != null)
				imports.get(index).setTime(time);
			imports.get(index).setAmount(amount);
			imports.get(index).setType(type);
			if (description != null)
				imports.get(index).setDescription(description);
			if (bankAccountNo != null)
				imports.get(index).setBankAccountNo(bankAccountNo);
			if (chequeNo != null)
				imports.get(index).setChequeNo(chequeNo);
			if (entityName != null)
				imports.get(index).setEntityName(entityName);
			if (items != null)
				imports.get(index).setItems(items);
			imports.get(index).setTransportTransactionNo(transportTransactionNo);
			return true;
		}
		return false;
	}

	public static int indexOf(int number) {
		for (int i = 0; i < imports.size(); i++) {
			if (imports.get(i).getNumber() == number)
				return i;
		}
		return -1;
	}

	public static Purchase get(int index) {
		if (index >= imports.size() || index == -1)
			return null;
		else
			return imports.get(index);
	}
}
