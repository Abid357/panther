package AppLogic;

import java.util.ArrayList;

import Core.Inventory;
import Core.Item;

public class Inventories {
	private static ArrayList<Inventory> inventories = new ArrayList<Inventory>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<Inventory> list) {
		inventories = list;
	}

	public static ArrayList<Inventory> getList() {
		return inventories;
	}

	public static void deleteList() {
		inventories = new ArrayList<Inventory>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.InventoryDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.InventoryDatabase.save();
	}

	public static boolean add(Inventory inventory) {
		if (indexOf(inventory.getNumber()) == -1)
			return inventories.add(inventory);
		else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= inventories.size() || index == -1)
			return false;
		else
			return (inventories.remove(index) != null);
	}

	public static boolean update(int number, ArrayList<Item> items) {
		int index = indexOf(number);
		if (index != -1) {
			if (items != null)
				inventories.get(index).setItems(items);
			return true;
		}
		return false;
	}

	public static int indexOf(int number) {
		if (number == -1)
			return -1;
		for (int i = 0; i < inventories.size(); i++) {
			if (inventories.get(i).getNumber() == number)
				return i;
		}
		return -1;
	}

	public static Inventory get(int index) {
		if (index >= inventories.size() || index == -1)
			return null;
		else
			return new Inventory(inventories.get(index));
	}
}
