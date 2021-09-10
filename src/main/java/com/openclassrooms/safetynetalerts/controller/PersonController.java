package com.openclassrooms.safetynetalerts.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.openclassrooms.safetynetalerts.model.Person;
import com.openclassrooms.safetynetalerts.service.PersonService;

@RestController
public class PersonController {

	@Autowired
	private PersonService personService;

	private static Logger logger = LoggerFactory.getLogger(PersonController.class);

	/**
	 * Read - Get all persons
	 * 
	 * @return - An Iterable object of Person full filled
	 */
	@GetMapping("/persons")
	public List<Person> getPersons() {
		return personService.getPersons();
	}

	/**
	 * Read - Get one person by his id (firstName + lastName)
	 * 
	 * @param firstName - The firstName of the person
	 * @param lastName  - The lastName of the person
	 * @return - An object Person full filled
	 */
	@GetMapping("/person/{firstName}/{lastName}")
	public Person getPerson(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
		return personService.getPerson(firstName, lastName);
	}

	/**
	 * Create - Add a new person
	 * 
	 * @param person - An object Person
	 * @return - The HTTP code "201" and URI if object created, HTTP code "204" if
	 *         not
	 */
	@PostMapping("/person")
	public ResponseEntity<Void> createPerson(@RequestBody Person person) {

		if (person.getFirstName() == null || person.getFirstName() == "") {
			logger.error("Person not created : no data in request body");
			return ResponseEntity.noContent().build();
		}

		Person personCreated = personService.savePerson(person);

		logger.info("Person created");
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{firstName}/{lastName}")
				.buildAndExpand(personCreated.getFirstName(), personCreated.getLastName()).toUri();

		return ResponseEntity.created(location).build();

	}

	/**
	 * Update - Update an existing person
	 * 
	 * @param firstName - The firstName of the person
	 * @param lastName  - The lastName of the person
	 * @param person    - An object Person
	 * @return - The Person object updated
	 */
	@PutMapping("/person/{firstName}/{lastName}")
	public Person updatePerson(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName,
			@RequestBody Person person) {
		// TODO manage possible error in return?
		Person currentPerson = personService.getPerson(firstName, lastName);
		if (currentPerson != null) {
			logger.info("Person updated");
			// Update all data received except first and last name
			String address = person.getAddress();
			if (address != null) {
				currentPerson.setAddress(address);
			}

			String city = person.getCity();
			if (city != null) {
				currentPerson.setCity(city);
			}

			String zip = person.getZip();
			if (zip != null) {
				currentPerson.setZip(zip);
			}

			String phone = person.getPhone();
			if (phone != null) {
				currentPerson.setPhone(phone);
			}

			String email = person.getEmail();
			if (email != null) {
				currentPerson.setEmail(email);
			}
			personService.savePerson(currentPerson);
			logger.info("Person updated");
			return currentPerson;
		} else {
			logger.error("The person to update doesn't exist");
			return null;
		}
	}

	/**
	 * Delete a person by his id
	 * 
	 * @param firstName - The firstName of the person
	 * @param lastName  - The lastName of the person
	 */
	@DeleteMapping("/person/{firstName}/{lastName}")
	public Person deletePerson(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
		personService.deletePerson(firstName, lastName);
		// TODO Test result
		return null;
	}
}
