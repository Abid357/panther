package AppLogic;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import Core.Item;
import Core.Transaction;

public class Transactions {
	private static ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<Transaction> list) {
		transactions = list;
	}

	public static ArrayList<Transaction> getList() {
		return transactions;
	}

	public static void deleteList() {
		transactions = new ArrayList<Transaction>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.TransactionDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.TransactionDatabase.save();
	}

	public static boolean add(Transaction transaction) {
		if (indexOf(transaction.getNumber()) == -1)
			return transactions.add(transaction);
		else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= transactions.size() || index == -1)
			return false;
		else {
			// int number = transactions.get(index).getNumber();
			// if (transactions.get(index).getClass().equals(Core.Import.class))
			// AppLogic.Imports.remove(AppLogic.Imports.indexOf(name));
			// if (transactions.get(index).getClass().equals(Core.Transport.class))
			// AppLogic.Transports.remove(AppLogic.Transports.indexOf(name));
			// if (transactions.get(index).getClass().equals(Core.Domestic.class))
			// AppLogic.Domestics.remove(AppLogic.Domestics.indexOf(name));
			// if (transactions.get(index).getClass().equals(Core.RenewalFee.class))
			// AppLogic.RenewalFees.remove(AppLogic.RenewalFees.indexOf(name));
			// if (transactions.get(index).getClass().equals(Core.Rent.class))
			// AppLogic.Rents.remove(AppLogic.Rents.indexOf(name));
			// if (transactions.get(index).getClass().equals(Core.Salary.class))
			// AppLogic.Salarys.remove(AppLogic.Salarys.indexOf(name));
			// if (transactions.get(index).getClass().equals(Core.Damage.class))
			// AppLogic.Damages.remove(AppLogic.Damages.indexOf(name));
			// if (transactions.get(index).getClass().equals(Core.Maintenance.class))
			// AppLogic.Maintenances.remove(AppLogic.Maintenances.indexOf(name));
			// if (transactions.get(index).getClass().equals(Core.Investment.class))
			// AppLogic.Investments.remove(AppLogic.Investments.indexOf(name));
			// if (transactions.get(index).getClass().equals(Core.Profit.class))
			// AppLogic.Profits.remove(AppLogic.Profits.indexOf(name));
			// if (transactions.get(index).getClass().equals(Core.Sale.class))
			// AppLogic.Sales.remove(AppLogic.Sales.indexOf(name));
			return (transactions.remove(index) != null);
		}
	}

	public static int max() {
		int max = -1;
		if (!transactions.isEmpty())
			max = transactions.get(0).getNumber();
		for (int i = 1; i < transactions.size(); i++)
			if (transactions.get(i).getNumber() > max)
				max = transactions.get(i).getNumber();
		return max;
	}

	public static boolean update(int number, Date date, Time time, double amount, char type, String description,
			String bankAccountNo, String chequeNo, String entityName, ArrayList<Item> items) {
		int index = indexOf(number);
		if (index != -1) {
			if (date != null)
				transactions.get(index).setDate(date);
			if (time != null)
				transactions.get(index).setTime(time);
			transactions.get(index).setAmount(amount);
			transactions.get(index).setType(type);
			if (description != null)
				transactions.get(index).setDescription(description);
			if (bankAccountNo != null)
				transactions.get(index).setBankAccountNo(bankAccountNo);
			if (chequeNo != null)
				transactions.get(index).setChequeNo(chequeNo);
			if (entityName != null)
				transactions.get(index).setEntityName(entityName);
			if (items != null)
				transactions.get(index).setItems(items);
			return true;
		}
		return false;
	}

	public static int indexOf(int number) {
		for (int i = 0; i < transactions.size(); i++) {
			if (transactions.get(i).getNumber() == number)
				return i;
		}
		return -1;
	}

	public static Transaction get(int index) {
		if (index >= transactions.size() || index == -1)
			return null;
		else
			return new Transaction(transactions.get(index));
	}

	public static ArrayList<Transaction> getPureTransactions() {
		ArrayList<Transaction> pureTransactions = new ArrayList<Transaction>();
		for (Transaction t : transactions)
			if (t.getClass().equals(Transaction.class))
				pureTransactions.add(new Transaction(t));
		return pureTransactions;
	}
}
