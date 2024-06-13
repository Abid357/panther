package AppLogic;

import java.util.ArrayList;

import Core.ProductTag;

public class ProductTags {
	private static ArrayList<ProductTag> productTags = new ArrayList<ProductTag>();
	private static boolean isDirty;
	
	public static boolean isDirty() {
		return isDirty;
	}
	
	public static void setDirty(boolean dirty) {
		isDirty = dirty;
	}

	public static void setList(ArrayList<ProductTag> list) {
		productTags = list;
	}

	public static ArrayList<ProductTag> getList() {
		return productTags;
	}

	public static void deleteList() {
		productTags = new ArrayList<ProductTag>();
	}

	public static boolean add(ProductTag productTag) {
		if (indexOf(productTag.getID()) == -1)
			if (AppLogic.OfferingTags.indexOf(productTag.getID()) == -1)
				if (AppLogic.PriceTags.add(productTag))
					return productTags.add(productTag);
				else
					return false;
			else
				return false;
		else
			return false;
	}

	public static boolean remove(int index) {
		if (index >= productTags.size() || index == -1)
			return false;
		else
			return (productTags.remove(index) != null);
	}

	public static boolean update(int ID, double price, String tag, int productCode) {
		int index = indexOf(ID);
		if (index != -1) {
			productTags.get(index).setPrice(price);
			if (tag != null)
				productTags.get(index).setTag(tag);
			productTags.get(index).setProductCode(productCode);
			return true;
		}
		return false;
	}

	public static int indexOf(int ID) {
		for (int i = 0; i < productTags.size(); i++) {
			if (productTags.get(i).getID() == ID)
				return i;
		}
		return -1;
	}

	public static ProductTag get(int index) {
		if (index >= productTags.size() || index == -1)
			return null;
		else
			return productTags.get(index);
	}

	public static ArrayList<ProductTag> searchByProductCode(int productCode) {
		ArrayList<ProductTag> tags = new ArrayList<ProductTag>();
		for (int i = 0; i < productTags.size(); i++)
			if (productTags.get(i).getProductCode() == productCode)
				tags.add(productTags.get(i));
		return tags;
	}
}
