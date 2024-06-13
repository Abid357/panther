package Core;

public class Warehouse {
	private int number;
	private String location;
	private int inventoryNo;

	public Warehouse(int number, String location, int inventoryNo) {
		super();
		this.number = number;
		this.location = location;
		this.inventoryNo = inventoryNo;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getInventoryNo() {
		return inventoryNo;
	}

	public void setInventoryNo(int inventoryNo) {
		this.inventoryNo = inventoryNo;
	}

	@Override
	public String toString() {
		return "Warehouse [number: " + number + ", location: " + location + ", inventoryNo: " + inventoryNo + "]";
	}
}
