package Core;

import java.sql.Date;

public class Emp_Partner extends Entity {
	private Date visaExpiry;
	private Date cardExpiry;
	private String licenseNumber;

	public Emp_Partner(String name, String contactPerson, String contactNumber, String location, Date visaExpiry, Date cardExpiry,
			String licenseNumber) {
		super(name, contactPerson, contactNumber, location);
		this.visaExpiry = visaExpiry;
		this.cardExpiry = cardExpiry;
		this.licenseNumber = licenseNumber;
	}

	public Date getVisaExpiry() {
		return visaExpiry;
	}

	public void setVisaExpiry(Date visaExpiry) {
		this.visaExpiry = visaExpiry;
	}
	
	public Date getCardExpiry() {
		return cardExpiry;
	}

	public void setCardExpiry(Date cardExpiry) {
		this.cardExpiry = cardExpiry;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	@Override
	public String toString() {
		return "Emp_Partner [visaExpiry: " + visaExpiry + ", licenseNumber: " + licenseNumber + "]";
	}
}
