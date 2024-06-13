package AppLogic;

import java.util.ArrayList;

import Core.Bank;

public class Banks {
	private static ArrayList<Bank> banks = new ArrayList<Bank>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<Bank> list) {
		banks = list;
	}

	public static ArrayList<Bank> getList() {
		ArrayList<Bank> copy = new ArrayList<Bank>();
		for (Bank b : banks)
			copy.add(b);
		return copy;
	}

	public static void deleteList() {
		banks = new ArrayList<Bank>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.BankDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.BankDatabase.save();
	}

	public static boolean add(Bank bank) {
		if (indexOf(bank.getAccountNo()) == -1)
			return banks.add(bank);
		else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= banks.size() || index == -1)
			return false;
		else
			return (banks.remove(index) != null);
	}

	public static boolean update(String accountNo, String name, double balance, ArrayList<Integer> loanTransactionNos,
			String entityName) {
		int index = indexOf(accountNo);
		if (index != -1) {
			if (accountNo != null)
				banks.get(index).setAccountNo(accountNo);
			if (name != null)
				banks.get(index).setName(name);
			banks.get(index).setBalance(balance);
			if (loanTransactionNos != null)
				banks.get(index).setLoanTransactionNos(loanTransactionNos);
			if (entityName != null)
				banks.get(index).setEntityName(entityName);
			return true;
		}
		return false;
	}

	public static int indexOf(String accountNo) {
		for (int i = 0; i < banks.size(); i++) {
			if (banks.get(i).getAccountNo().equals(accountNo))
				return i;
		}
		return -1;
	}

	public static Bank get(int index) {
		if (index >= banks.size() || index == -1)
			return null;
		else
			return banks.get(index);
	}

	public static ArrayList<Bank> searchByEntityName(String entityName) {
		ArrayList<Bank> entityBanks = new ArrayList<Bank>();
		for (int i = 0; i < banks.size(); i++)
			if (banks.get(i).getEntityName().equals(entityName))
				entityBanks.add(banks.get(i));
		return entityBanks;
	}

	public static Bank searchByLoanTransactionNo(int loanTransactionNo) {
		for (int i = 0; i < banks.size(); i++)
			for (int j = 0; j < banks.get(i).getLoanTransactionNos().size(); j++)
				if (banks.get(i).getLoanTransactionNos().get(j) == loanTransactionNo)
					return banks.get(i);
		return null;
	}
}
