package com.openclassrooms.safetynetalerts.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetynetalerts.model.DataSafetyNet;
import com.openclassrooms.safetynetalerts.model.Person;

@Repository
public class PersonRepository {

	@Autowired
	private DataSafetyNet dataSafetyNet;

	public List<Person> findAll() {
		return dataSafetyNet.getPersons();
	}

	public Optional<Person> findByFirstNameAndLastName(String firstName, String lastName) {
		// TODO Method to find and return the person
		return null;
	}

	public Person save(Person person) {
		// TODO Add person in data.json
		// manage possible error in return ?
		return null;
	}

	public void deleteByFirstNameAndLastName(String firstName, String lastName) {
		// TODO Delete person in data.json
		// manage possible error in return ?

	}

}
