package AppLogic;

import java.util.ArrayList;

import Core.Offering;

public class Offerings {
	private static ArrayList<Offering> offerings = new ArrayList<Offering>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<Offering> list) {
		offerings = list;
	}

	public static ArrayList<Offering> getList() {
		return offerings;
	}

	public static void deleteList() {
		offerings = new ArrayList<Offering>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.OfferingDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.OfferingDatabase.save();
	}

	public static boolean add(Offering offering) {
		if (indexOf(offering.getOfferingName(), offering.getSupplierName()) == -1)
			return offerings.add(offering);
		else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= offerings.size() || index == -1)
			return false;
		else
			return (offerings.remove(index) != null);
	}

	public static boolean update(String offeringName, char type, String supplierName) {
		int index = indexOf(offeringName, supplierName);
		if (index != -1) {
			offerings.get(index).setType(type);
			return true;
		}
		return false;
	}

	public static int indexOf(String offeringName, String supplierName) {
		for (int i = 0; i < offerings.size(); i++) {
			if (offerings.get(i).getOfferingName().equals(offeringName)
					&& offerings.get(i).getSupplierName().equals(supplierName))
				return i;
		}
		return -1;
	}

	public static Offering get(int index) {
		if (index >= offerings.size() || index == -1)
			return null;
		else
			return offerings.get(index);
	}
	
	public static ArrayList<Offering> getAllMaterials(){
		ArrayList<Offering> materials = new ArrayList<Offering>();
		for (int i = 0; i < offerings.size(); i++)
			if (offerings.get(i).getType() == 'M')
				materials.add(offerings.get(i));
		return materials;
	}
	
public static ArrayList<Offering> getAllServices(){
	ArrayList<Offering> services = new ArrayList<Offering>();
	for (int i = 0; i < offerings.size(); i++)
		if (offerings.get(i).getType() == 'S')
			services.add(offerings.get(i));
	return services;
	}
}
