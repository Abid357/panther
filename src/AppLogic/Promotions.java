package AppLogic;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import Core.Item;
import Core.Promotion;

public class Promotions {
	private static ArrayList<Promotion> promotions = new ArrayList<Promotion>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<Promotion> list) {
		promotions = list;
	}

	public static ArrayList<Promotion> getList() {
		return promotions;
	}

	public static void deleteList() {
		promotions = new ArrayList<Promotion>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.PromotionDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.PromotionDatabase.save();
	}

	public static boolean add(Promotion promotion) {
		if (indexOf(promotion.getNumber()) == -1) {
			if (AppLogic.Transactions.add(promotion))
				return promotions.add(promotion);
			else
				return false;
		} else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= promotions.size() || index == -1)
			return false;
		else
			return (promotions.remove(index) != null);
	}

	public static boolean update(int number, Date date, Time time, double amount, char type, String description,
			String bankAccountNo, String chequeNo, String entityName, ArrayList<Item> items,
			int inventoryNo, boolean isSample) {
		int index = indexOf(number);
		if (index != -1) {
			if (date != null)
				promotions.get(index).setDate(date);
			if (time != null)
				promotions.get(index).setTime(time);
			promotions.get(index).setAmount(amount);
			promotions.get(index).setType(type);
			if (description != null)
				promotions.get(index).setDescription(description);
			if (bankAccountNo != null)
				promotions.get(index).setBankAccountNo(bankAccountNo);
			if (chequeNo != null)
				promotions.get(index).setChequeNo(chequeNo);
			if (entityName != null)
				promotions.get(index).setEntityName(entityName);
			if (items != null)
				promotions.get(index).setItems(items);
			promotions.get(index).setInventoryNo(inventoryNo);
			promotions.get(index).setSample(isSample);
			return true;
		}
		return false;
	}

	public static int indexOf(int number) {
		for (int i = 0; i < promotions.size(); i++) {
			if (promotions.get(i).getNumber() == number)
				return i;
		}
		return -1;
	}

	public static Promotion get(int index) {
		if (index >= promotions.size() || index == -1)
			return null;
		else
			return promotions.get(index);
	}
}
