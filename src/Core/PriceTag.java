package Core;

public class PriceTag {
	private int ID;
	private double price;
	private String tag;

	public PriceTag(int iD, double price, String tag) {
		super();
		ID = iD;
		this.price = price;
		this.tag = tag;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "Pricetag [ID: " + ID + ", price: " + price + ", tag: " + tag + "]";
	}
}
