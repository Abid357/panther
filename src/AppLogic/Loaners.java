package AppLogic;

import java.util.ArrayList;

import Core.Entity;
import Core.Loaner;

public class Loaners {
	private static ArrayList<Loaner> loaners = new ArrayList<Loaner>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<Loaner> list) {
		loaners = list;
	}

	public static ArrayList<Loaner> getList() {
		ArrayList<Loaner> copyLoaners = new ArrayList<Loaner>();
		for (Loaner l : loaners)
			copyLoaners.add(l);
		return copyLoaners;
	}

	public static void deleteList() {
		loaners = new ArrayList<Loaner>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.LoanerDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.LoanerDatabase.save();
	}

	public static boolean add(Loaner loaner) {
		if (indexOf(loaner.getName()) == -1) {
			if (AppLogic.Entities.add(loaner))
				return loaners.add(loaner);
			else
				return false;
		} else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= loaners.size() || index == -1)
			return false;
		else
			return (loaners.remove(index) != null);
	}

	public static boolean update(String name, String contactPerson, String contactNumber, String location,
			double balance) {
		int index = indexOf(name);
		if (index != -1) {
			if (contactPerson != null)
				loaners.get(index).setContactPerson(contactPerson);
			if (contactNumber != null)
				loaners.get(index).setContactNumber(contactNumber);
			if (location != null)
				loaners.get(index).setLocation(location);
			loaners.get(index).setBalance(balance);
			return true;
		}
		return false;
	}

	public static int indexOf(String name) {
		for (int i = 0; i < loaners.size(); i++) {
			if (loaners.get(i).getName().equals(name))
				return i;
		}
		return -1;
	}

	public static Loaner get(int index) {
		if (index >= loaners.size() || index == -1)
			return null;
		else
			return loaners.get(index);
	}

	public static Loaner convert(Entity entity, double balance) {
		Loaner loaner = new Loaner(entity.getName(), entity.getContactPerson(), entity.getContactNumber(),
				entity.getLocation(), balance);
		if (loaners.add(loaner))
			return loaner;
		else
			return null;
	}
}
