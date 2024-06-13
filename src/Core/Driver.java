package Core;

public class Driver extends Entity {
	private String plateNo;

	public Driver(String name, String contactPerson, String contactNumber, String location, String plateNo) {
		super(name, contactPerson, contactNumber, location);
		this.plateNo = plateNo;
	}

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	@Override
	public String toString() {
		return "Driver [plateNo: " + plateNo + "]";
	}
}
