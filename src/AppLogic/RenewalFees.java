package AppLogic;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import Core.Item;
import Core.RenewalFee;

public class RenewalFees {
	private static ArrayList<RenewalFee> renewalFees = new ArrayList<RenewalFee>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<RenewalFee> list) {
		renewalFees = list;
	}

	public static ArrayList<RenewalFee> getList() {
		return renewalFees;
	}

	public static void deleteList() {
		renewalFees = new ArrayList<RenewalFee>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.RenewalFeeDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.RenewalFeeDatabase.save();
	}

	public static boolean add(RenewalFee renewalFee) {
		if (indexOf(renewalFee.getNumber()) == -1) {
			if (AppLogic.Transactions.add(renewalFee))
				return renewalFees.add(renewalFee);
			else
				return false;
		} else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= renewalFees.size() || index == -1)
			return false;
		else
			return (renewalFees.remove(index) != null);
	}

	public static boolean update(int number, Date date, Time time, double amount, char type, String description,
			String bankAccountNo, String chequeNo, String entityName, ArrayList<Item> items,
			String renewalType) {
		int index = indexOf(number);
		if (index != -1) {
			if (date != null)
				renewalFees.get(index).setDate(date);
			if (time != null)
				renewalFees.get(index).setTime(time);
			renewalFees.get(index).setAmount(amount);
			renewalFees.get(index).setType(type);
			if (description != null)
				renewalFees.get(index).setDescription(description);
			if (bankAccountNo != null)
				renewalFees.get(index).setBankAccountNo(bankAccountNo);
			if (chequeNo != null)
				renewalFees.get(index).setChequeNo(chequeNo);
			if (entityName != null)
				renewalFees.get(index).setEntityName(entityName);
			if (items != null)
				renewalFees.get(index).setItems(items);
			if (renewalType != null)
				renewalFees.get(index).setRenewalType(renewalType);
			return true;
		}
		return false;
	}

	public static int indexOf(int number) {
		for (int i = 0; i < renewalFees.size(); i++) {
			if (renewalFees.get(i).getNumber() == number)
				return i;
		}
		return -1;
	}

	public static RenewalFee get(int index) {
		if (index >= renewalFees.size() || index == -1)
			return null;
		else
			return renewalFees.get(index);
	}
}
