package AppLogic;

import java.util.ArrayList;

import Core.Beverage;

public class Beverages {
	private static ArrayList<Beverage> beverages = new ArrayList<Beverage>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<Beverage> list) {
		beverages = list;
	}

	public static ArrayList<Beverage> getList() {
		return beverages;
	}

	public static void deleteList() {
		beverages = new ArrayList<Beverage>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.BeverageDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.BeverageDatabase.save();
	}

	public static boolean add(Beverage beverage) {
		if (indexOf(beverage.getCode()) == -1)
			if (AppLogic.DryFoods.indexOf(beverage.getCode()) == -1)
				if (AppLogic.Products.add(beverage))
					return beverages.add(beverage);
				else
					return false;
			else
				return false;
		else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= beverages.size() || index == -1)
			return false;
		else
			return (beverages.remove(index) != null);
	}

	public static boolean update(int code, String name, double size, String unit, int piecesPerCarton, String barcode,
			String beverageType) {
		int index = indexOf(code);
		if (index != -1) {
			if (name != null)
				beverages.get(index).setName(name);
			beverages.get(index).setSize(size);
			if (unit != null)
				beverages.get(index).setUnit(unit);
			beverages.get(index).setPiecesPerCarton(piecesPerCarton);
			if (barcode != null)
				beverages.get(index).setBarcode(barcode);
			if (beverageType != null)
				beverages.get(index).setBeverageType(beverageType);
			return true;
		}
		return false;
	}

	public static int indexOf(int code) {
		for (int i = 0; i < beverages.size(); i++) {
			if (beverages.get(i).getCode() == code)
				return i;
		}
		return -1;
	}

	public static Beverage get(int index) {
		if (index >= beverages.size() || index == -1)
			return null;
		else
			return beverages.get(index);
	}
}
