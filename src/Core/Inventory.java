package Core;

import java.util.ArrayList;

public class Inventory {
	private int number;
	private ArrayList<Item> items;

	public Inventory(int number, ArrayList<Item> items) {
		super();
		this.number = number;
		this.items = items;
	}

	public Inventory(Inventory inventory) {
		this.number = inventory.number;
		ArrayList<Item> items = new ArrayList<Item>();
		for (Item i : inventory.items)
			items.add(new Item(i));
		this.items = items;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public ArrayList<Item> getItems() {
		ArrayList<Item> items = new ArrayList<Item>();
		for (Item i : this.items)
			items.add(new Item(i));
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public Item getItem(String tag, double price) {
		if (items != null) {
			PriceTag priceTag = AppLogic.PriceTags.get(AppLogic.PriceTags.indexOf(tag, price));
			if (priceTag != null)
				for (int i = 0; i < items.size(); i++)
					if (items.get(i).getPriceTagID() == priceTag.getID())
						return new Item(items.get(i));
		}
		return null;
	}

	@Override
	public String toString() {
		return "Inventory [number: " + number + ", items: " + items + "]";
	}
}
