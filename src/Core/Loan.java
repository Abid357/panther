package Core;

import java.sql.Date;
import java.sql.Time;

public class Loan extends Transaction {
	private boolean isActive;
	private int loanTransactionNo;

	public Loan(int number, Date date, Time time, double amount, char type, String description, String bankAccountNo,
			String chequeNo, String entityName, boolean isActive, int loanTransactionNo) {
		super(number, date, time, amount, type, description, bankAccountNo, chequeNo, entityName, null);
		this.isActive = isActive;
		this.loanTransactionNo = loanTransactionNo;
	}

	public Loan(Loan loan) {
		super(loan);
		this.isActive = loan.isActive;
		this.loanTransactionNo = loan.loanTransactionNo;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getLoanTransactionNo() {
		return loanTransactionNo;
	}

	public void setLoanTransactionNo(int loanTransactionNo) {
		this.loanTransactionNo = loanTransactionNo;
	}

	@Override
	public String toString() {
		return "Loan [isActive: " + isActive + ", loanTransactionNo: " + loanTransactionNo + "]";
	}
}
