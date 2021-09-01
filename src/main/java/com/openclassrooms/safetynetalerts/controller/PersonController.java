package com.openclassrooms.safetynetalerts.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.safetynetalerts.model.Person;
import com.openclassrooms.safetynetalerts.service.PersonService;

@RestController
public class PersonController {

	@Autowired
	private PersonService personService;

	/**
	 * Read - Get all persons
	 * 
	 * @return - An Iterable object of Person full filled
	 */
	@GetMapping("/person")
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
	public Optional<Person> getPerson(@PathVariable("firstName") String firstName,
			@PathVariable("lastName") String lastName) {
		return personService.getPerson(firstName, lastName);
	}

	/**
	 * Create - Add a new person
	 * 
	 * @param person - An object Person
	 * @return - The person object saved
	 */
	@PostMapping("/person")
	public Person createPerson(@RequestBody Person person) {
		// TODO manage possible error in return?
		return personService.savePerson(person);
	}

	/**
	 * Update - Update an existing person
	 * 
	 * @param firstName - The firstName of the person
	 * @param lastName  - The lastName of the person
	 * @param person    - An object Person
	 * @return - The person object updated
	 */
	@PutMapping("/person/{firstName}/{lastName}")
	public Person updatePerson(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName,
			@RequestBody Person person) {
		// TODO manage possible error in return?
		Optional<Person> p = personService.getPerson(firstName, lastName);
		if (p.isPresent()) {
			Person currentPerson = p.get();

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
			return currentPerson;
		} else {
			return null;
		}
	}

	/**
	 * Delete an employee by his id
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
