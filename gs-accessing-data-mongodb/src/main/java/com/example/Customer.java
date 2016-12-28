package com.example;

import org.springframework.data.annotation.Id;

/**
 * MongoDB stores data in collections. Spring Data MongoDB will map the class
 * Customer into a collection called customer. If you want to change the name of
 * the collection, you can use Spring Data MongoDB’s @Document annotation on the
 * class.
 * 
 */
public class Customer {

	@Id
	public String id;
	public String firstName;
	public String lastName;

	public Customer() {
	}

	public Customer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return String.format("Customer[id=%s, firstName='%s', lastName='%s']", id, firstName, lastName);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
