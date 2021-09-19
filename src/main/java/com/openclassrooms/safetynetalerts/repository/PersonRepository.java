package com.openclassrooms.safetynetalerts.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetynetalerts.model.DataSafetyNet;
import com.openclassrooms.safetynetalerts.model.Person;

@Repository
public class PersonRepository {

	@Autowired
	private DataSafetyNet dataSafetyNet;

	private static Logger logger = LoggerFactory.getLogger(PersonRepository.class);

	public List<Person> findAll() {
		logger.debug("Persons list found");
		return dataSafetyNet.getPersons();
	}

	public Person findByFirstNameAndLastName(String firstName, String lastName) {
		List<Person> personsList = dataSafetyNet.getPersons();
		Person resultPerson = null;

		for (Person p : personsList) {
			if (p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {
				resultPerson = p;
			}
		}
		logger.debug("Person found by first and last name");
		return resultPerson;
	}

	public List<Person> findByFirstName(String firstName) {
		List<Person> personsList = dataSafetyNet.getPersons();
		List<Person> resultPerson = new ArrayList<Person>();

		for (Person p : personsList) {
			if (p.getFirstName().equals(firstName)) {
				resultPerson.add(p);
			}
		}
		logger.debug("Persons list found by first name");
		return resultPerson;
	}

	public List<Person> findByLastName(String lastName) {
		List<Person> personsList = dataSafetyNet.getPersons();
		List<Person> resultPersonList = new ArrayList<Person>();

		for (Person p : personsList) {
			if (p.getLastName().equals(lastName)) {
				resultPersonList.add(p);
			}
		}
		logger.debug("Persons list found by last name");
		return resultPersonList;
	}

	public List<Person> findByAddress(String address) {
		List<Person> personsList = dataSafetyNet.getPersons();
		List<Person> resultPersonList = new ArrayList<Person>();

		for (Person p : personsList) {
			if (p.getAddress().equals(address)) {
				resultPersonList.add(p);
			}
		}
		logger.debug("Persons list found by address");
		return resultPersonList;
	}

	public Person save(Person person) {

		List<Person> personsInData = dataSafetyNet.getPersons();
		boolean exist = false;
		// If person's first and last names already in list, update
		for (int i = 0; i < personsInData.size() && !exist; i++) {
			if (personsInData.get(i).getFirstName().equals(person.getFirstName())
					&& personsInData.get(i).getLastName().equals(person.getLastName())) {

				personsInData.get(i).setAddress(person.getAddress());
				personsInData.get(i).setCity(person.getCity());
				personsInData.get(i).setZip(person.getZip());
				personsInData.get(i).setEmail(person.getEmail());
				personsInData.get(i).setPhone(person.getPhone());
				exist = true;
				logger.debug("Person updated");
			}
		}
		// If person not in list, add
		if (exist == false) {
			personsInData.add(person);
			logger.debug("Person created");
		}
		// Update the global data
		dataSafetyNet.setPersons(personsInData);

		return person;
	}

	public void deleteByFirstNameAndLastName(String firstName, String lastName) {

		List<Person> personsInData = dataSafetyNet.getPersons();
		boolean deleted = false;
		// If person's first and last names exist in list, remove it from list
		for (int i = 0; i < personsInData.size() && !deleted; i++) {
			if (personsInData.get(i).getFirstName().equals(firstName)
					&& personsInData.get(i).getLastName().equals(lastName)) {
				personsInData.remove(i);
				dataSafetyNet.setPersons(personsInData);
				deleted = true;
				logger.debug("Person deleted");
			}
		}

	}

}
