package AppLogic;

import java.util.ArrayList;

import Core.Customer;

public class Customers {
	private static ArrayList<Customer> customers = new ArrayList<Customer>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<Customer> list) {
		customers = list;
	}

	public static ArrayList<Customer> getList() {
		return customers;
	}

	public static void deleteList() {
		customers = new ArrayList<Customer>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.CustomerDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.CustomerDatabase.save();
	}

	public static boolean add(Customer customer) {
		if (indexOf(customer.getName()) == -1) {
			if (AppLogic.Entities.add(customer))
				return customers.add(customer);
			else
				return false;
		} else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= customers.size() || index == -1)
			return false;
		else
			return (customers.remove(index) != null);
	}

	public static boolean update(String name, String contactPerson, String contactNumber, String location) {
		int index = indexOf(name);
		if (index != -1) {
			if (contactPerson != null)
				customers.get(index).setContactPerson(contactPerson);
			if (contactNumber != null)
				customers.get(index).setContactNumber(contactNumber);
			if (location != null)
				customers.get(index).setLocation(location);
			return true;
		}
		return false;
	}

	public static int indexOf(String name) {
		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getName().equals(name))
				return i;
		}
		return -1;
	}

	public static Customer get(int index) {
		if (index >= customers.size() || index == -1)
			return null;
		else
			return customers.get(index);
	}
}
