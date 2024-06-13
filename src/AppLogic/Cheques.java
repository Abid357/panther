package AppLogic;

import java.sql.Date;
import java.util.ArrayList;

import Core.Cheque;

public class Cheques {
	private static ArrayList<Cheque> cheques = new ArrayList<Cheque>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<Cheque> list) {
		cheques = list;
	}

	public static ArrayList<Cheque> getList() {
		return cheques;
	}

	public static void deleteList() {
		cheques = new ArrayList<Cheque>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.ChequeDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.ChequeDatabase.save();
	}

	public static boolean add(Cheque cheque) {
		if (indexOf(cheque.getNumber(), cheque.getBankAccountNo()) == -1)
			return cheques.add(cheque);
		else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= cheques.size() || index == -1)
			return false;
		else
			return (cheques.remove(index) != null);
	}

	public static boolean update(String name, Date dueDate, String bankAccountNo) {
		int index = indexOf(name, bankAccountNo);
		if (index != -1) {
			if (dueDate != null)
				cheques.get(index).setDueDate(dueDate);
			if (bankAccountNo != null)
				cheques.get(index).setBankAccountNo(bankAccountNo);
			return true;
		}
		return false;
	}

	public static int indexOf(String number, String bankAccountNo) {
		for (int i = 0; i < cheques.size(); i++) {
			if (cheques.get(i).getNumber().equals(number) && cheques.get(i).getBankAccountNo().equals(bankAccountNo))
				return i;
		}
		return -1;
	}

	public static Cheque get(int index) {
		if (index >= cheques.size() || index == -1)
			return null;
		else
			return cheques.get(index);
	}

	public static ArrayList<Cheque> searchByBankAccountNo(String bankAccountNo) {
		ArrayList<Cheque> bankCheques = new ArrayList<Cheque>();
		for (int i = 0; i < cheques.size(); i++)
			if (cheques.get(i).getBankAccountNo().equals(bankAccountNo))
				bankCheques.add(cheques.get(i));
		return bankCheques;
	}
}
