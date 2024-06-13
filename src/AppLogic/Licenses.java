package AppLogic;

import java.sql.Date;
import java.util.ArrayList;

import Core.License;

public class Licenses {
	private static ArrayList<License> licenses = new ArrayList<License>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<License> list) {
		licenses = list;
	}

	public static ArrayList<License> getList() {
		return licenses;
	}

	public static void deleteList() {
		licenses = new ArrayList<License>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.LicenseDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.LicenseDatabase.save();
	}

	public static boolean add(License license) {
		if (indexOf(license.getNumber()) == -1)
			return licenses.add(license);
		else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= licenses.size() || index == -1)
			return false;
		else
			return (licenses.remove(index) != null);
	}

	public static boolean update(String number, String name, Date immCardRenewal, Date poboxRenewal,
			Date licenseRenewal, ArrayList<Integer> rentTransactionNos, ArrayList<Integer> renewalFeeTransactionNos) {
		int index = indexOf(number);
		if (index != -1) {
			if (name != null)
				licenses.get(index).setName(name);
			if (immCardRenewal != null)
				licenses.get(index).setImmCardRenewal(immCardRenewal);
			if (poboxRenewal != null)
				licenses.get(index).setPoboxRenewal(poboxRenewal);
			if (licenseRenewal != null)
				licenses.get(index).setLicenseRenewal(licenseRenewal);
			if (rentTransactionNos != null)
				licenses.get(index).setRentTransactionNos(rentTransactionNos);
			if (renewalFeeTransactionNos != null)
				licenses.get(index).setRenewalFeeTransactionNos(renewalFeeTransactionNos);
			return true;
		}
		return false;
	}

	public static int indexOf(String number) {
		for (int i = 0; i < licenses.size(); i++) {
			if (licenses.get(i).getNumber().equals(number))
				return i;
		}
		return -1;
	}

	public static License get(int index) {
		if (index >= licenses.size() || index == -1)
			return null;
		else
			return licenses.get(index);
	}

	public static License searchByRent(int rentTransactionNo) {
		for (int i = 0; i < licenses.size(); i++) {
			ArrayList<Integer> rentTransactionNos = licenses.get(i).getRentTransactionNos();
			for (int j = 0; j < rentTransactionNos.size(); j++)
				if (rentTransactionNos.get(j) == rentTransactionNo)
					return licenses.get(i);
		}
		return null;
	}
	
	public static License searchByRenewalFee(int renewalFeeTransactionNo) {
		for (int i = 0; i < licenses.size(); i++) {
			ArrayList<Integer> renewalFeeTransactionNos = licenses.get(i).getRenewalFeeTransactionNos();
			for (int j = 0; j < renewalFeeTransactionNos.size(); j++)
				if (renewalFeeTransactionNos.get(j) == renewalFeeTransactionNo)
					return licenses.get(i);
		}
		return null;
	}
}
