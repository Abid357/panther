package AppLogic;

import java.util.ArrayList;

import Core.Vehicle;

public class Vehicles {
	private static ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<Vehicle> list) {
		vehicles = list;
	}

	public static ArrayList<Vehicle> getList() {
		return vehicles;
	}

	public static void deleteList() {
		vehicles = new ArrayList<Vehicle>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.VehicleDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.VehicleDatabase.save();
	}

	public static boolean add(Vehicle vehicle) {
		if (indexOf(vehicle.getPlateNo()) == -1)
			return vehicles.add(vehicle);
		else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= vehicles.size() || index == -1)
			return false;
		else
			return (vehicles.remove(index) != null);
	}

	public static boolean update(String plateNo, String location, String type) {
		int index = indexOf(plateNo);
		if (index != -1) {
			if (location != null)
				vehicles.get(index).setLocation(location);
			if (type != null)
				vehicles.get(index).setType(type);
			return true;
		}
		return false;
	}

	public static int indexOf(String plateNo) {
		for (int i = 0; i < vehicles.size(); i++) {
			if (vehicles.get(i).getPlateNo().equals(plateNo))
				return i;
		}
		return -1;
	}

	public static Vehicle get(int index) {
		if (index >= vehicles.size() || index == -1)
			return null;
		else
			return vehicles.get(index);
	}
}
