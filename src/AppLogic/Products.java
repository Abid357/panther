package AppLogic;

import java.util.ArrayList;

import Core.Product;

public class Products {
	private static ArrayList<Product> products = new ArrayList<Product>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<Product> list) {
		products = list;
	}

	public static ArrayList<Product> getList() {
		return products;
	}

	public static void deleteList() {
		products = new ArrayList<Product>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.ProductDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.ProductDatabase.save();
	}

	public static boolean add(Product product) {
		if (indexOf(product.getCode()) == -1)
			return products.add(product);
		else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= products.size() || index == -1)
			return false;
		else
			return (products.remove(index) != null);
	}

	public static boolean update(int code, String name, double size, String unit, int piecesPerCarton, String barcode) {
		int index = indexOf(code);
		if (index != -1) {
			if (name != null)
				products.get(index).setName(name);
			products.get(index).setSize(size);
			if (unit != null)
				products.get(index).setUnit(unit);
			products.get(index).setPiecesPerCarton(piecesPerCarton);
			if (barcode != null)
				products.get(index).setBarcode(barcode);
			return true;
		}
		return false;
	}

	public static int indexOf(int code) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getCode() == code)
				return i;
		}
		return -1;
	}

	public static Product get(int index) {
		if (index >= products.size() || index == -1)
			return null;
		else
			return products.get(index);
	}
}
