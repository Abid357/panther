package AppLogic;

import java.util.ArrayList;

import Core.DryFood;

public class DryFoods {
	private static ArrayList<DryFood> dryFoods = new ArrayList<DryFood>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<DryFood> list) {
		dryFoods = list;
	}

	public static ArrayList<DryFood> getList() {
		return dryFoods;
	}

	public static void deleteList() {
		dryFoods = new ArrayList<DryFood>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.DryFoodDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.DryFoodDatabase.save();
	}

	public static boolean add(DryFood dryFood) {
		if (indexOf(dryFood.getCode()) == -1)
			if (AppLogic.Beverages.indexOf(dryFood.getCode()) == -1)
				if (AppLogic.Products.add(dryFood))
					return dryFoods.add(dryFood);
				else
					return false;
			else
				return false;
		else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= dryFoods.size() || index == -1)
			return false;
		else
			return (dryFoods.remove(index) != null);
	}

	public static boolean update(int code, String name, double size, String unit, int piecesPerCarton, String barcode,
			String dryFoodType) {
		int index = indexOf(code);
		if (index != -1) {
			if (name != null)
				dryFoods.get(index).setName(name);
			dryFoods.get(index).setSize(size);
			if (unit != null)
				dryFoods.get(index).setUnit(unit);
			dryFoods.get(index).setPiecesPerCarton(piecesPerCarton);
			if (barcode != null)
				dryFoods.get(index).setBarcode(barcode);
			if (dryFoodType != null)
				dryFoods.get(index).setDryFoodType(dryFoodType);
			return true;
		}
		return false;
	}

	public static int indexOf(int code) {
		for (int i = 0; i < dryFoods.size(); i++) {
			if (dryFoods.get(i).getCode() == code)
				return i;
		}
		return -1;
	}

	public static DryFood get(int index) {
		if (index >= dryFoods.size() || index == -1)
			return null;
		else
			return dryFoods.get(index);
	}
}
