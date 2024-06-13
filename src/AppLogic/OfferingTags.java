package AppLogic;

import java.util.ArrayList;

import Core.OfferingTag;

public class OfferingTags {
	private static ArrayList<OfferingTag> offeringTags = new ArrayList<OfferingTag>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<OfferingTag> list) {
		offeringTags = list;
	}

	public static ArrayList<OfferingTag> getList() {
		return offeringTags;
	}

	public static void deleteList() {
		offeringTags = new ArrayList<OfferingTag>();
	}

	public static boolean add(OfferingTag offeringTag) {
		if (indexOf(offeringTag.getID()) == -1)
			if (AppLogic.ProductTags.indexOf(offeringTag.getID()) == -1)
				if (AppLogic.PriceTags.add(offeringTag))
					return offeringTags.add(offeringTag);
				else
					return false;
			else
				return false;
		else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= offeringTags.size() || index == -1)
			return false;
		else
			return (offeringTags.remove(index) != null);
	}

	public static boolean update(int ID, double price, String tag, String offeringName, String supplierName) {
		int index = indexOf(ID);
		if (index != -1) {
			offeringTags.get(index).setPrice(price);
			if (tag != null)
				offeringTags.get(index).setTag(tag);
			if (offeringName != null)
				offeringTags.get(index).setOfferingName(offeringName);
			if (supplierName != null)
				offeringTags.get(index).setSupplierName(supplierName);
			return true;
		}
		return false;
	}

	public static int indexOf(int ID) {
		for (int i = 0; i < offeringTags.size(); i++) {
			if (offeringTags.get(i).getID() == ID)
				return i;
		}
		return -1;
	}

	public static OfferingTag get(int index) {
		if (index >= offeringTags.size() || index == -1)
			return null;
		else
			return offeringTags.get(index);
	}

	public static ArrayList<OfferingTag> searchByOfferingName(String offeringName, String supplierName) {
		ArrayList<OfferingTag> tags = new ArrayList<OfferingTag>();
		for (int i = 0; i < offeringTags.size(); i++)
			if (offeringTags.get(i).getOfferingName().equals(offeringName)
					&& offeringTags.get(i).getSupplierName().equals(supplierName))
				tags.add(offeringTags.get(i));
		return tags;
	}
}
