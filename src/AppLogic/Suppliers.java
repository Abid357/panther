package AppLogic;

import java.util.ArrayList;

import Core.Entity;
import Core.Offering;
import Core.Supplier;

public class Suppliers {
	private static ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<Supplier> list) {
		suppliers = list;
	}

	public static ArrayList<Supplier> getList() {
		return suppliers;
	}

	public static void deleteList() {
		suppliers = new ArrayList<Supplier>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.SupplierDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.SupplierDatabase.save();
	}

	public static boolean add(Supplier supplier) {
		if (indexOf(supplier.getName()) == -1) {
			if (AppLogic.Entities.add(supplier))
				return suppliers.add(supplier);
			else
				return false;
		} else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= suppliers.size() || index == -1)
			return false;
		else
			return (suppliers.remove(index) != null);
	}

	public static boolean update(String name, String contactPerson, String contactNumber, String location,
			int inventoryNo, ArrayList<Offering> offerings) {
		int index = indexOf(name);
		if (index != -1) {
			if (contactPerson != null)
				suppliers.get(index).setContactPerson(contactPerson);
			if (contactNumber != null)
				suppliers.get(index).setContactNumber(contactNumber);
			if (location != null)
				suppliers.get(index).setLocation(location);
			suppliers.get(index).setInventoryNo(inventoryNo);
			if (offerings != null)
				suppliers.get(index).setOfferings(offerings);
			return true;
		}
		return false;
	}

	public static int indexOf(String name) {
		for (int i = 0; i < suppliers.size(); i++) {
			if (suppliers.get(i).getName().equals(name))
				return i;
		}
		return -1;
	}

	public static Supplier get(int index) {
		if (index >= suppliers.size() || index == -1)
			return null;
		else
			return suppliers.get(index);
	}
	
	public static Supplier searchByInventory(int inventoryNo) {
		for (int i = 0; i < suppliers.size(); i++) {
			if (suppliers.get(i).getInventoryNo() == (inventoryNo))
				return suppliers.get(i);
		}
		return null;
	}

	public static Supplier convert(Entity entity, int inventoryNo, ArrayList<Offering> offerings) {
		Supplier supplier = new Supplier(entity.getName(), entity.getContactPerson(), entity.getContactNumber(),
				entity.getLocation(), inventoryNo, offerings);
		return supplier;
	}
}
