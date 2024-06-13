package Core;

public class Entity {
	private String name;
	private String contactPerson;
	private String contactNumber;
	private String location;

	public Entity(String name, String contactPerson, String contactNumber, String location) {
		super();
		this.name = name;
		this.contactPerson = contactPerson;
		this.contactNumber = contactNumber;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Entity [name: " + name + ", contactPerson: " + contactPerson + ", contactNumber: " + contactNumber
				+ ", location: " + location + "]";
	}
}
