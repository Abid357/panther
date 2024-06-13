package Core;

import java.util.ArrayList;

public class Bank {
	private String accountNo;
	private String name;
	private double balance;
	private ArrayList<Integer> loanTransactionNos;
	private String entityName;

	public Bank(String accountNo, String name, double balance, ArrayList<Integer> loanTransactionNos,
			String entityName) {
		super();
		this.accountNo = accountNo;
		this.name = name;
		this.balance = balance;
		this.loanTransactionNos = loanTransactionNos;
		this.entityName = entityName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public ArrayList<Integer> getLoanTransactionNos() {
		ArrayList<Integer> copy = new ArrayList<Integer>();
		for (Integer i : loanTransactionNos)
			copy.add(new Integer(i));
		return copy;
	}

	public void setLoanTransactionNos(ArrayList<Integer> loanTransactionNos) {
		this.loanTransactionNos = loanTransactionNos;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	@Override
	public String toString() {
		return "Bank [accountNo: " + accountNo + ", name: " + name + ", balance: " + balance + ", loanTransactionNos: "
				+ loanTransactionNos + ", entityName: " + entityName + "]";
	}
}
