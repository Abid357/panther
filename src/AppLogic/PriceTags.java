package AppLogic;

import java.util.ArrayList;

import Core.PriceTag;

public class PriceTags {
	private static ArrayList<PriceTag> priceTags = new ArrayList<PriceTag>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<PriceTag> list) {
		priceTags = list;
	}

	public static ArrayList<PriceTag> getList() {
		return priceTags;
	}

	public static void deleteList() {
		priceTags = new ArrayList<PriceTag>();
	}

	public static boolean loadList() {
		isDirty = false;
		deleteList();
		return DBMS.PriceTagDatabase.load();
	}

	public static boolean saveList() {
		isDirty = false;
		return DBMS.PriceTagDatabase.save();
	}

	public static boolean add(PriceTag priceTag) {
		if (indexOf(priceTag.getID()) == -1 && indexOf(priceTag.getTag(), priceTag.getPrice()) == -1)
			return priceTags.add(priceTag);
		else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= priceTags.size() || index == -1)
			return false;
		else {
			PriceTag priceTag = priceTags.get(index);
			if (priceTag.getClass().equals(Core.ProductTag.class))
				AppLogic.ProductTags.remove(AppLogic.ProductTags.indexOf(priceTag.getID()));
			if (priceTag.getClass().equals(Core.OfferingTag.class))
				AppLogic.OfferingTags.remove(AppLogic.OfferingTags.indexOf(priceTag.getID()));
			return (priceTags.remove(index) != null);
		}
	}

	public static boolean update(int ID, double price, String tag) {
		int index = indexOf(ID);
		if (index != -1) {
			priceTags.get(index).setPrice(price);
			if (tag != null)
				priceTags.get(index).setTag(tag);
			return true;
		}
		return false;
	}
	
	public static int max() {
		int max = -1;
		if (!priceTags.isEmpty())
			max = priceTags.get(0).getID();
		for (int i = 1; i < priceTags.size(); i++)
			if (priceTags.get(i).getID() > max)
				max = priceTags.get(i).getID();
		return max;
	}

	public static int indexOf(int ID) {
		for (int i = 0; i < priceTags.size(); i++) {
			if (priceTags.get(i).getID() == ID)
				return i;
		}
		return -1;
	}

	public static int indexOf(String tag, double price) {
		for (int i = 0; i < priceTags.size(); i++) {
			if (priceTags.get(i).getTag().equals(tag) && priceTags.get(i).getPrice() == price)
				return i;
		}
		return -1;
	}

	public static PriceTag get(int index) {
		if (index >= priceTags.size() || index == -1)
			return null;
		else
			return priceTags.get(index);
	}
}
