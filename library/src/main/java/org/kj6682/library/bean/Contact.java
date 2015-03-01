package org.kj6682.library.bean;


public class Contact {

	private final long id;
	private final String firstName;
	private final String lastName;
	
	
	public Contact(long id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Contact [id=").append(id).append(", firstName=").append(firstName).append(", lastName=")
				.append(lastName).append("]");
		return builder.toString();
	}


	
	
}
