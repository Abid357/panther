package Facade;

import java.util.ArrayList;

import Core.Inventory;
import Core.Item;

public class Inventories {

	public static boolean addStock(Inventory inventory, Item item) {
		if (inventory == null || item == null)
			return false;
		AppLogic.Inventories.setDirty(true);
		ArrayList<Item> items = inventory.getItems();
		for (int i = 0; i < items.size(); i++)
			if (items.get(i).getPriceTagID() == item.getPriceTagID()) {
				items.get(i).setQuantity(items.get(i).getQuantity() + item.getQuantity());
				return AppLogic.Inventories.update(inventory.getNumber(), items);
			}
		return addItem(inventory, item);
	}

	public static boolean removeStock(Inventory inventory, Item item) {
		if (inventory == null || item == null)
			return false;
		AppLogic.Inventories.setDirty(true);
		ArrayList<Item> items = inventory.getItems();
		for (int i = 0; i < items.size(); i++)
			if (items.get(i).getPriceTagID() == item.getPriceTagID()) {
				items.get(i).setQuantity(items.get(i).getQuantity() - item.getQuantity());
				if (items.get(i).getQuantity() <= 0)
					return removeItem(inventory, item);
				else
					return AppLogic.Inventories.update(inventory.getNumber(), items);
			}
		return false;
	}

	public static boolean addItem(Inventory inventory, Item item) {
		if (inventory == null || item == null)
			return false;
		AppLogic.Inventories.setDirty(true);
		ArrayList<Item> items = inventory.getItems();
		items.add(item);
		return AppLogic.Inventories.update(inventory.getNumber(), items);
	}

	public static boolean removeItem(Inventory inventory, Item item) {
		if (inventory == null || item == null)
			return false;
		AppLogic.Inventories.setDirty(true);
		ArrayList<Item> items = inventory.getItems();
		for (int i = 0; i < items.size(); i++)
			if (items.get(i).getPriceTagID() == item.getPriceTagID())
				items.remove(i);
		return AppLogic.Inventories.update(inventory.getNumber(), items);
	}
}
