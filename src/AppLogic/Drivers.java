package AppLogic;

import java.util.ArrayList;

import Core.Driver;

public class Drivers {
	private static ArrayList<Driver> drivers = new ArrayList<Driver>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<Driver> list) {
		drivers = list;
	}

	public static ArrayList<Driver> getList() {
		return drivers;
	}

	public static void deleteList() {
		drivers = new ArrayList<Driver>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.DriverDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.DriverDatabase.save();
	}

	public static boolean add(Driver driver) {
		if (indexOf(driver.getName()) == -1) {
			if (AppLogic.Entities.add(driver))
				return drivers.add(driver);
			else
				return false;
		} else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= drivers.size() || index == -1)
			return false;
		else
			return (drivers.remove(index) != null);
	}

	public static boolean update(String name, String contactPerson, String contactNumber, String location, String plateNo) {
		int index = indexOf(name);
		if (index != -1) {
			if (contactPerson != null)
				drivers.get(index).setContactPerson(contactPerson);
			if (contactNumber != null)
				drivers.get(index).setContactNumber(contactNumber);
			if (location != null)
				drivers.get(index).setLocation(location);
			if (plateNo != null)
				drivers.get(index).setPlateNo(plateNo);
			return true;
		}
		return false;
	}

	public static int indexOf(String name) {
		for (int i = 0; i < drivers.size(); i++) {
			if (drivers.get(i).getName().equals(name))
				return i;
		}
		return -1;
	}

	public static Driver get(int index) {
		if (index >= drivers.size() || index == -1)
			return null;
		else
			return drivers.get(index);
	}
}
