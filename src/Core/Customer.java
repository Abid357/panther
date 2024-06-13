package Core;

public class Customer extends Entity {

	public Customer(String name, String contactPerson, String contactNumber, String location) {
		super(name, contactPerson, contactNumber, location);
	}

	@Override
	public String toString() {
		return "Customer [getName(): " + getName() + ", getContactPerson(): " + getContactPerson()
				+ ", getContactNumber(): " + getContactNumber() + ", getLocation(): " + getLocation() + ", toString(): "
				+ super.toString() + ", getClass(): " + getClass() + ", hashCode(): " + hashCode() + "]";
	}

}
