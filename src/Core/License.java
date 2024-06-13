package Core;

import java.sql.Date;
import java.util.ArrayList;

public class License {
	private String number;
	private String name;
	private Date immCardRenewal;
	private Date poboxRenewal;
	private Date licenseRenewal;
	ArrayList<Integer> rentTransactionNos;
	ArrayList<Integer> renewalFeeTransactionNos;

	public License(String number, String name, Date immCardRenewal, Date poboxRenewal, Date licenseRenewal,
			ArrayList<Integer> rentTransactionNos, ArrayList<Integer> renewalFeeTransactionNos) {
		super();
		this.number = number;
		this.name = name;
		this.immCardRenewal = immCardRenewal;
		this.poboxRenewal = poboxRenewal;
		this.licenseRenewal = licenseRenewal;
		this.rentTransactionNos = rentTransactionNos;
		this.renewalFeeTransactionNos = renewalFeeTransactionNos;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getImmCardRenewal() {
		return immCardRenewal;
	}

	public void setImmCardRenewal(Date immCardRenewal) {
		this.immCardRenewal = immCardRenewal;
	}

	public Date getPoboxRenewal() {
		return poboxRenewal;
	}

	public void setPoboxRenewal(Date poboxRenewal) {
		this.poboxRenewal = poboxRenewal;
	}

	public Date getLicenseRenewal() {
		return licenseRenewal;
	}

	public void setLicenseRenewal(Date licenseRenewal) {
		this.licenseRenewal = licenseRenewal;
	}

	public ArrayList<Integer> getRentTransactionNos() {
		return rentTransactionNos;
	}

	public void setRentTransactionNos(ArrayList<Integer> rentTransactionNos) {
		this.rentTransactionNos = rentTransactionNos;
	}

	public ArrayList<Integer> getRenewalFeeTransactionNos() {
		return renewalFeeTransactionNos;
	}

	public void setRenewalFeeTransactionNos(ArrayList<Integer> renewalFeeTransactionNos) {
		this.renewalFeeTransactionNos = renewalFeeTransactionNos;
	}

	@Override
	public String toString() {
		return "License [number: " + number + ", name: " + name + ", immCardRenewal: " + immCardRenewal
				+ ", poboxRenewal: " + poboxRenewal + ", licenseRenewal: " + licenseRenewal + ", rentTransactionNos: "
				+ rentTransactionNos + ", renewalFeeTransactionNos: " + renewalFeeTransactionNos + "]";
	}
}
