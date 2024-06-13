package Core;

public class DryFood extends Product {
	private String dryFoodType;

	public DryFood(int code, String name, double size, String unit, int piecesPerCarton, String barcode,
			String dryFoodType) {
		super(code, name, size, unit, piecesPerCarton, barcode);
		this.dryFoodType = dryFoodType;
	}

	public String getDryFoodType() {
		return dryFoodType;
	}

	public void setDryFoodType(String dryFoodType) {
		this.dryFoodType = dryFoodType;
	}

	@Override
	public String toString() {
		return "DryFood [dryFoodType: " + dryFoodType + "]";
	}
}
