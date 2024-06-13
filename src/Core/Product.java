package Core;

public class Product {
	private int code;
	private String name;
	private double size;
	private String unit;
	private int piecesPerCarton;
	private String barcode;

	public Product(int code, String name, double size, String unit, int piecesPerCarton, String barcode) {
		super();
		this.code = code;
		this.name = name;
		this.size = size;
		this.unit = unit;
		this.piecesPerCarton = piecesPerCarton;
		this.barcode = barcode;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) { 
		this.name = name;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getPiecesPerCarton() {
		return piecesPerCarton;
	}

	public void setPiecesPerCarton(int piecesPerCarton) {
		this.piecesPerCarton = piecesPerCarton;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	@Override
	public String toString() {
		return "Product [code: " + code + ", name: " + name + ", size: " + size + ", unit: " + unit
				+ ", piecesPerCarton: " + piecesPerCarton + ", barcode: " + barcode + "]";
	}
}
