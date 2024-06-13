package Core;

public class Item {
	private int priceTagID;
	private int quantity;

	public Item(int priceTagID, int quantity) {
		super();
		this.priceTagID = priceTagID;
		this.quantity = quantity;
	}

	public Item(Item item) {
		this.priceTagID = item.priceTagID;
		this.quantity = item.quantity;
	}

	public int getPriceTagID() {
		return priceTagID;
	}

	public void setPriceTagID(int priceTagID) {
		this.priceTagID = priceTagID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Item [priceTagID: " + priceTagID + ", quantity: " + quantity + "]";
	}
}
