package AppLogic;

import java.sql.Date;
import java.util.ArrayList;

import Core.Emp_Partner;

public class Emp_Partners {
	private static ArrayList<Emp_Partner> emp_partners = new ArrayList<Emp_Partner>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<Emp_Partner> list) {
		emp_partners = list;
	}

	public static ArrayList<Emp_Partner> getList() {
		return emp_partners;
	}

	public static void deleteList() {
		emp_partners = new ArrayList<Emp_Partner>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.Emp_PartnerDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.Emp_PartnerDatabase.save();
	}

	public static boolean add(Emp_Partner emp_partner) {
		if (indexOf(emp_partner.getName()) == -1) {
			if (AppLogic.Entities.add(emp_partner))
				return emp_partners.add(emp_partner);
			else
				return false;
		} else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= emp_partners.size() || index == -1)
			return false;
		else
			return (emp_partners.remove(index) != null);
	}

	public static boolean update(String name, String contactPerson, String contactNumber, String location, Date visaExpiry, Date cardExpiry, String licenseNumber) {
		int index = indexOf(name);
		if (index != -1) {
			if (contactPerson != null)
				emp_partners.get(index).setContactPerson(contactPerson);
			if (contactNumber != null)
				emp_partners.get(index).setContactNumber(contactNumber);
			if (location != null)
				emp_partners.get(index).setLocation(location);
			if (visaExpiry != null)
				emp_partners.get(index).setVisaExpiry(visaExpiry);
			if (cardExpiry != null)
				emp_partners.get(index).setCardExpiry(cardExpiry);
			if (licenseNumber != null)
				emp_partners.get(index).setLicenseNumber(licenseNumber);
			return true;
		}
		return false;
	}
	
	public static int indexOf(String name) {
		for (int i = 0; i < emp_partners.size(); i++) {
			if (emp_partners.get(i).getName().equals(name))
				return i;
		}
		return -1;
	}
	
	public static Emp_Partner get(int index) {
		if (index >= emp_partners.size() || index == -1)
			return null;
		else
			return emp_partners.get(index);
	}
}
