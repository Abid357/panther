package AppLogic;

import java.util.ArrayList;

import Core.Entity;

public class Entities {
	private static ArrayList<Entity> entities = new ArrayList<Entity>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}
	
	public static void setList(ArrayList<Entity> list) {
		entities = list;
	}

	public static ArrayList<Entity> getList() {
		return entities;
	}

	public static void deleteList() {
		entities = new ArrayList<Entity>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.EntityDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.EntityDatabase.save();
	}

	public static boolean add(Entity entity) {
		if (indexOf(entity.getName()) == -1)
			return entities.add(entity);
		else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= entities.size() || index == -1)
			return false;
		else {
			String name = entities.get(index).getName();
			if (entities.get(index).getClass().equals(Core.Supplier.class))
				AppLogic.Suppliers.remove(AppLogic.Suppliers.indexOf(name));
			if (entities.get(index).getClass().equals(Core.Loaner.class))
				AppLogic.Loaners.remove(AppLogic.Loaners.indexOf(name));
			if (entities.get(index).getClass().equals(Core.Investor.class))
				AppLogic.Investors.remove(AppLogic.Investors.indexOf(name));
			if (entities.get(index).getClass().equals(Core.Emp_Partner.class))
				AppLogic.Emp_Partners.remove(AppLogic.Emp_Partners.indexOf(name));
			if (entities.get(index).getClass().equals(Core.Driver.class))
				AppLogic.Drivers.remove(AppLogic.Drivers.indexOf(name));
			if (entities.get(index).getClass().equals(Core.Customer.class))
				AppLogic.Customers.remove(AppLogic.Customers.indexOf(name));
			return (entities.remove(index) != null);
		}
	}

	public static boolean update(String name, String contactPerson, String contactNumber, String location) {
		int index = indexOf(name);
		if (index != -1) {
			if (contactPerson != null)
				entities.get(index).setContactPerson(contactPerson);
			if (contactNumber != null)
				entities.get(index).setContactNumber(contactNumber);
			if (location != null)
				entities.get(index).setLocation(location);
			return true;
		}
		return false;
	}

	public static int indexOf(String name) {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).getName().equals(name))
				return i;
		}
		return -1;
	}

	public static Entity get(int index) {
		if (index >= entities.size() || index == -1)
			return null;
		else
			return entities.get(index);
	}

	public static ArrayList<Entity> getAllCompanies() {
		ArrayList<Entity> companies = new ArrayList<Entity>();
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).getContactPerson() == null)
				companies.add(entities.get(i));
			else if (!entities.get(i).getName().equals(entities.get(i).getContactPerson()))
				companies.add(entities.get(i));
		}
		return companies;
	}

	public static ArrayList<Entity> getAllIndividuals() {
		ArrayList<Entity> individuals = new ArrayList<Entity>();
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).getName().equals(entities.get(i).getContactPerson()))
				individuals.add(entities.get(i));
		}
		return individuals;
	}
}
