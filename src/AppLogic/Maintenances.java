package AppLogic;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import Core.Item;
import Core.Maintenance;

public class Maintenances {
	private static ArrayList<Maintenance> maintenances = new ArrayList<Maintenance>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<Maintenance> list) {
		maintenances = list;
	}

	public static ArrayList<Maintenance> getList() {
		return maintenances;
	}

	public static void deleteList() {
		maintenances = new ArrayList<Maintenance>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.MaintenanceDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.MaintenanceDatabase.save();
	}

	public static boolean add(Maintenance maintenance) {
		if (indexOf(maintenance.getNumber()) == -1) {
			if (AppLogic.Transactions.add(maintenance))
				return maintenances.add(maintenance);
			else
				return false;
		} else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= maintenances.size() || index == -1)
			return false;
		else
			return (maintenances.remove(index) != null);
	}

	public static boolean update(int number, Date date, Time time, double amount, char type, String description,
			String bankAccountNo, String chequeNo, String entityName, ArrayList<Item> items,
			String vehiclePlateNo) {
		int index = indexOf(number);
		if (index != -1) {
			if (date != null)
				maintenances.get(index).setDate(date);
			if (time != null)
				maintenances.get(index).setTime(time);
			maintenances.get(index).setAmount(amount);
			maintenances.get(index).setType(type);
			if (description != null)
				maintenances.get(index).setDescription(description);
			if (bankAccountNo != null)
				maintenances.get(index).setBankAccountNo(bankAccountNo);
			if (chequeNo != null)
				maintenances.get(index).setChequeNo(chequeNo);
			if (entityName != null)
				maintenances.get(index).setEntityName(entityName);
			if (items != null)
				maintenances.get(index).setItems(items);
			if (vehiclePlateNo != null)
				maintenances.get(index).setVehiclePlateNo(vehiclePlateNo);
			return true;
		}
		return false;
	}

	public static int indexOf(int number) {
		for (int i = 0; i < maintenances.size(); i++) {
			if (maintenances.get(i).getNumber() == number)
				return i;
		}
		return -1;
	}

	public static Maintenance get(int index) {
		if (index >= maintenances.size() || index == -1)
			return null;
		else
			return maintenances.get(index);
	}
}
