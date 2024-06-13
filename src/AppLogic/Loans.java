package AppLogic;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import Core.Item;
import Core.Loan;

public class Loans {
	private static ArrayList<Loan> loans = new ArrayList<Loan>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<Loan> list) {
		loans = list;
	}

	public static ArrayList<Loan> getList() {
		ArrayList<Loan> copy = new ArrayList<Loan>();
		for (Loan l : loans)
			copy.add(new Loan(l));
		return copy;
	}

	public static void deleteList() {
		loans = new ArrayList<Loan>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.LoanDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.LoanDatabase.save();
	}

	public static boolean add(Loan loan) {
		if (indexOf(loan.getNumber()) == -1) {
			if (AppLogic.Transactions.add(loan))
				return loans.add(loan);
			else
				return false;
		} else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= loans.size() || index == -1)
			return false;
		else
			return (loans.remove(index) != null);
	}

	public static boolean update(int number, Date date, Time time, double amount, char type, String description,
			String bankAccountNo, String chequeNo, String entityName, ArrayList<Item> items, boolean isActive,
			int loanTransactionNo) {
		int index = indexOf(number);
		if (index != -1) {
			if (date != null)
				loans.get(index).setDate(date);
			if (time != null)
				loans.get(index).setTime(time);
			loans.get(index).setAmount(amount);
			loans.get(index).setType(type);
			if (description != null)
				loans.get(index).setDescription(description);
			if (bankAccountNo != null)
				loans.get(index).setBankAccountNo(bankAccountNo);
			if (chequeNo != null)
				loans.get(index).setChequeNo(chequeNo);
			if (entityName != null)
				loans.get(index).setEntityName(entityName);
			if (items != null)
				loans.get(index).setItems(items);
			loans.get(index).setActive(isActive);
			loans.get(index).setLoanTransactionNo(loanTransactionNo);
			return true;
		}
		return false;
	}

	public static int indexOf(int number) {
		for (int i = 0; i < loans.size(); i++) {
			if (loans.get(i).getNumber() == number)
				return i;
		}
		return -1;
	}

	public static Loan get(int index) {
		if (index >= loans.size() || index == -1)
			return null;
		else
			return loans.get(index);
	}
	
	public static ArrayList<Loan> getOldLoans(){
		ArrayList<Loan> oldLoans = new ArrayList<Loan>();
		for (Loan l : loans)
			if (!l.isActive())
				oldLoans.add(new Loan(l));
		return oldLoans;
	}
}
