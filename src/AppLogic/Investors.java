package AppLogic;

import java.util.ArrayList;

import Core.Investor;

public class Investors {
	private static ArrayList<Investor> investors = new ArrayList<Investor>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<Investor> list) {
		investors = list;
	}

	public static ArrayList<Investor> getList() {
		return investors;
	}

	public static void deleteList() {
		investors = new ArrayList<Investor>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.InvestorDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.InvestorDatabase.save();
	}

	public static boolean add(Investor investor) {
		if (indexOf(investor.getName()) == -1) {
			if (AppLogic.Entities.add(investor))
				return investors.add(investor);
			else
				return false;
		} else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= investors.size() || index == -1)
			return false;
		else
			return (investors.remove(index) != null);
	}

	public static boolean update(String name, String contactPerson, String contactNumber, String location,
			double invested, double profited, float profitRate) {
		int index = indexOf(name);
		if (index != -1) {
			if (contactPerson != null)
				investors.get(index).setContactPerson(contactPerson);
			if (contactNumber != null)
				investors.get(index).setContactNumber(contactNumber);
			if (location != null)
				investors.get(index).setLocation(location);
			investors.get(index).setInvested(invested);
			investors.get(index).setProfited(profited);
			investors.get(index).setProfitRate(profitRate);
			return true;
		}
		return false;
	}

	public static int indexOf(String name) {
		for (int i = 0; i < investors.size(); i++) {
			if (investors.get(i).getName().equals(name))
				return i;
		}
		return -1;
	}

	public static Investor get(int index) {
		if (index >= investors.size() || index == -1)
			return null;
		else
			return investors.get(index);
	}
}
