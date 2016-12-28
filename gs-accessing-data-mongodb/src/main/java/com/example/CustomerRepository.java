package com.example;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="customers", path="customers")
public interface CustomerRepository extends MongoRepository<Customer, String> {

	/**
	 * In a typical Java application, you write a class that implements
	 * CustomerRepository and craft the queries yourself. What makes Spring Data
	 * MongoDB so useful is the fact that you don’t have to create this
	 * implementation. Spring Data MongoDB creates it on the fly when you run
	 * the application.
	 * 
	 * @param fristName
	 * @return
	 */
	public Customer findByFirstName(@Param("firstname")String fristName);

	public List<Customer> findByLastName(@Param("lastname")String lastName);
}
