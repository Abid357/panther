package Core;

public class Beverage extends Product {
	private String beverageType;

	public Beverage(int code, String name, double size, String unit, int piecesPerCarton, String barcode,
			String beverageType) {
		super(code, name, size, unit, piecesPerCarton, barcode);
		this.beverageType = beverageType;
	}

	public String getBeverageType() {
		return beverageType;
	}

	public void setBeverageType(String beverageType) {
		this.beverageType = beverageType;
	}

	@Override
	public String toString() {
		return "Beverage [beverageType: " + beverageType + "]";
	}
}
