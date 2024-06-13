package AppLogic;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import Core.Item;
import Core.Rent;

public class Rents {
	private static ArrayList<Rent> rents = new ArrayList<Rent>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<Rent> list) {
		rents = list;
	}

	public static ArrayList<Rent> getList() {
		return rents;
	}

	public static void deleteList() {
		rents = new ArrayList<Rent>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.RentDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.RentDatabase.save();
	}

	public static boolean add(Rent rent) {
		if (indexOf(rent.getNumber()) == -1) {
			if (AppLogic.Transactions.add(rent))
				return rents.add(rent);
			else
				return false;
		} else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= rents.size() || index == -1)
			return false;
		else
			return (rents.remove(index) != null);
	}

	public static boolean update(int number, Date date, Time time, double amount, char type, String description,
			String bankAccountNo, String chequeNo, String entityName, ArrayList<Item> items,
			String rentType) {
		int index = indexOf(number);
		if (index != -1) {
			if (date != null)
				rents.get(index).setDate(date);
			if (time != null)
				rents.get(index).setTime(time);
			rents.get(index).setAmount(amount);
			rents.get(index).setType(type);
			if (description != null)
				rents.get(index).setDescription(description);
			if (bankAccountNo != null)
				rents.get(index).setBankAccountNo(bankAccountNo);
			if (chequeNo != null)
				rents.get(index).setChequeNo(chequeNo);
			if (entityName != null)
				rents.get(index).setEntityName(entityName);
			if (items != null)
				rents.get(index).setItems(items);
			if (rentType != null)
				rents.get(index).setRentType(rentType);
			return true;
		}
		return false;
	}

	public static int indexOf(int number) {
		for (int i = 0; i < rents.size(); i++) {
			if (rents.get(i).getNumber() == number)
				return i;
		}
		return -1;
	}

	public static Rent get(int index) {
		if (index >= rents.size() || index == -1)
			return null;
		else
			return rents.get(index);
	}
}
