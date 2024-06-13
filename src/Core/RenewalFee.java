package Core;

import java.sql.Date;
import java.sql.Time;

public class RenewalFee extends Transaction {
	private String renewalType;

	public RenewalFee(int number, Date date, Time time, double amount, char type, String description,
			String bankAccountNo, String chequeNo, String entityName,
			String renewalType) {
		super(number, date, time, amount, type, description, bankAccountNo, chequeNo, entityName, null);
		this.renewalType = renewalType;
	}

	public String getRenewalType() {
		return this.renewalType;
	}

	public void setRenewalType(String renewalType) {
		this.renewalType = renewalType;
	}

	@Override
	public String toString() {
		return "RenewalFee [renewalType: " + renewalType + "]";
	}
}
