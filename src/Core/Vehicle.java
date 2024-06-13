package Core;

public class Vehicle {
	private String plateNo;
	private String location;
	private String type;

	public Vehicle(String plateNo, String location, String type) {
		super();
		this.plateNo = plateNo;
		this.location = location;
		this.type = type;
	}

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Vehicle [plateNo: " + plateNo + ", location: " + location + ", type: " + type + "]";
	}
}
