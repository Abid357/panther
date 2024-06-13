package Core;

public class ProductTag extends PriceTag {
	private int productCode;

	public ProductTag(int ID, double price, String tag, int productCode) {
		super(ID, price, tag);
		this.productCode = productCode;
	}

	public int getProductCode() {
		return productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	@Override
	public String toString() {
		return "ProductTag [productCode: " + productCode + "]";
	}
}
