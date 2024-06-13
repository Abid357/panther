package AppLogic;

import java.util.ArrayList;

import Core.Warehouse;

public class Warehouses {
	private static ArrayList<Warehouse> warehouses = new ArrayList<Warehouse>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<Warehouse> list) {
		warehouses = list;
	}

	public static ArrayList<Warehouse> getList() {
		return warehouses;
	}

	public static void deleteList() {
		warehouses = new ArrayList<Warehouse>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.WarehouseDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.WarehouseDatabase.save();
	}

	public static boolean add(Warehouse warehouse) {
		if (indexOf(warehouse.getNumber(), warehouse.getLocation()) == -1)
			return warehouses.add(warehouse);
		else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= warehouses.size() || index == -1)
			return false;
		else
			return (warehouses.remove(index) != null);
	}

	public static boolean update(int number, String location, int inventoryNo) {
		int index = indexOf(number, location);
		if (index != -1) {
			warehouses.get(index).setInventoryNo(inventoryNo);
			return true;
		}
		return false;
	}

	public static int indexOf(int number, String location) {
		for (int i = 0; i < warehouses.size(); i++) {
			if (warehouses.get(i).getNumber() == number && warehouses.get(i).getLocation().equals(location))
				return i;
		}
		return -1;
	}

	public static Warehouse get(int index) {
		if (index >= warehouses.size() || index == -1)
			return null;
		else
			return warehouses.get(index);
	}
	
	public static Warehouse searchByInventory(int inventoryNo) {
		for (int i = 0; i < warehouses.size(); i++) {
			if (warehouses.get(i).getInventoryNo() == (inventoryNo))
				return warehouses.get(i);
		}
		return null;
	}
}
