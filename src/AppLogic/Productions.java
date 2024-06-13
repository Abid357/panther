package AppLogic;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import Core.Item;
import Core.Production;

public class Productions {
	private static ArrayList<Production> productions = new ArrayList<Production>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<Production> list) {
		productions = list;
	}

	public static ArrayList<Production> getList() {
		return productions;
	}

	public static void deleteList() {
		productions = new ArrayList<Production>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.ProductionDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.ProductionDatabase.save();
	}

	public static boolean add(Production production) {
		if (indexOf(production.getID()) == -1)
			return productions.add(production);
		else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= productions.size() || index == -1)
			return false;
		else 
			return (productions.remove(index) != null);
	}

	public static boolean update(int ID, Date startDate, Date endDate, int cartons, String notes, int productTag,
			int transportTransactionNo, HashMap<Integer, ArrayList<Item>> items) {
		int index = indexOf(ID);
		if (index != -1) {
			if (startDate != null)
				productions.get(index).setStartDate(startDate);
			if (endDate != null)
				productions.get(index).setEndDate(endDate);
			productions.get(index).setCartons(cartons);
			if (notes != null)
				productions.get(index).setNotes(notes);
			productions.get(index).setProductTag(productTag);
			productions.get(index).setTransportTransactionNo(transportTransactionNo);
			if (items != null)
				productions.get(index).setItems(items);
			return true;
		}
		return false;
	}

	public static int indexOf(int ID) {
		for (int i = 0; i < productions.size(); i++) {
			if (productions.get(i).getID() == ID)
				return i;
		}
		return -1;
	}

	public static Production get(int index) {
		if (index >= productions.size() || index == -1)
			return null;
		else
			return productions.get(index);
	}
}
