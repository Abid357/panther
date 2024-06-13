package AppLogic;

import java.util.ArrayList;

import Core.Pocket;

public class Pockets {
	private static ArrayList<Pocket> pockets = new ArrayList<Pocket>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<Pocket> list) {
		pockets = list;
	}

	public static ArrayList<Pocket> getList() {
		return pockets;
	}

	public static void deleteList() {
		pockets = new ArrayList<Pocket>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.PocketDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.PocketDatabase.save();
	}

	public static boolean add(Pocket pocket) {
		if (indexOf(pocket.getName()) == -1)
			return pockets.add(pocket);
		else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= pockets.size() || index == -1)
			return false;
		else
			return (pockets.remove(index) != null);
	}

	public static boolean update(String name, double balance) {
		int index = indexOf(name);
		if (index != -1) {
			pockets.get(index).setBalance(balance);
			return true;
		}
		return false;
	}

	public static int indexOf(String name) {
		for (int i = 0; i < pockets.size(); i++) {
			if (pockets.get(i).getName().equals(name))
				return i;
		}
		return -1;
	}

	public static Pocket get(int index) {
		if (index >= pockets.size() || index == -1)
			return null;
		else
			return new Pocket(pockets.get(index));
	}
}
