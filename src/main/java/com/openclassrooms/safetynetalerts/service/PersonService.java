package com.openclassrooms.safetynetalerts.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetynetalerts.model.Person;
import com.openclassrooms.safetynetalerts.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	private static Logger logger = LoggerFactory.getLogger(PersonService.class);

	public List<Person> getPersons() {
		logger.debug("Persons list to find");
		return personRepository.findAll();

	}

	public Person getPerson(String firstName, String lastName) {
		logger.debug("Person to find by first and last name");
		return personRepository.findByFirstNameAndLastName(firstName, lastName);
	}

	public Person savePerson(Person person) {
		Person savedPerson = personRepository.save(person);
		logger.debug("Person to save");
		return savedPerson;

	}

	public void deletePerson(String firstName, String lastName) {
		logger.debug("Person to delete");
		personRepository.deleteByFirstNameAndLastName(firstName, lastName);
	}
}
