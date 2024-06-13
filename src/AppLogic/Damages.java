package AppLogic;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import Core.Damage;
import Core.Item;

public class Damages {
	private static ArrayList<Damage> damages = new ArrayList<Damage>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<Damage> list) {
		damages = list;
	}

	public static ArrayList<Damage> getList() {
		return damages;
	}

	public static void deleteList() {
		damages = new ArrayList<Damage>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.DamageDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.DamageDatabase.save();
	}

	public static boolean add(Damage damage) {
		if (indexOf(damage.getNumber()) == -1) {
			if (AppLogic.Transactions.add(damage)) {
				return damages.add(damage);}
			else
				return false;
		} else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= damages.size() || index == -1)
			return false;
		else
			return (damages.remove(index) != null);
	}

	public static boolean update(int number, Date date, Time time, double amount, char type, String description,
			String bankAccountNo, String chequeNo, String entityName, ArrayList<Item> items, int inventoryNo) {
		int index = indexOf(number);
		if (index != -1) {
			if (date != null)
				damages.get(index).setDate(date);
			if (time != null)
				damages.get(index).setTime(time);
			damages.get(index).setAmount(amount);
			damages.get(index).setType(type);
			if (description != null)
				damages.get(index).setDescription(description);
			if (bankAccountNo != null)
				damages.get(index).setBankAccountNo(bankAccountNo);
			if (chequeNo != null)
				damages.get(index).setChequeNo(chequeNo);
			if (entityName != null)
				damages.get(index).setEntityName(entityName);
			if (items != null)
				damages.get(index).setItems(items);
			damages.get(index).setInventoryNo(inventoryNo);
			return true;
		}
		return false;
	}

	public static int indexOf(int number) {
		for (int i = 0; i < damages.size(); i++) {
			if (damages.get(i).getNumber() == number)
				return i;
		}
		return -1;
	}

	public static Damage get(int index) {
		if (index >= damages.size() || index == -1)
			return null;
		else
			return damages.get(index);
	}
}
