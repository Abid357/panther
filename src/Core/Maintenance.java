package Core;

import java.sql.Date;
import java.sql.Time;

public class Maintenance extends Transaction {
	private String vehiclePlateNo;

	public Maintenance(int number, Date date, Time time, double amount, char type, String description,
			String bankAccountNo, String chequeNo, String vehiclePlateNo) {
		super(number, date, time, amount, type, description, bankAccountNo, chequeNo, null, null);
		this.vehiclePlateNo = vehiclePlateNo;
	}

	public String getVehiclePlateNo() {
		return vehiclePlateNo;
	}

	public void setVehiclePlateNo(String vehiclePlateNo) {
		this.vehiclePlateNo = vehiclePlateNo;
	}

	@Override
	public String toString() {
		return "Maintenance [vehiclePlateNo: " + vehiclePlateNo + "]";
	}
}
