package com.openclassrooms.safetynetalerts.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DataSafetyNet {

	private List<Person> persons;
	private List<FireStation> firestations;
	private List<MedicalRecord> medicalrecords;

	/**
	 * @return the persons
	 */
	public List<Person> getPersons() {
		return persons;
	}

	/**
	 * @param persons - the list persons to set
	 */
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	/**
	 * @return the fireStations
	 */
	public List<FireStation> getFirestations() {
		return firestations;
	}

	/**
	 * @param fireStations - the list fireStations to set
	 */
	public void setFirestations(List<FireStation> firestations) {
		this.firestations = firestations;
	}

	/**
	 * @return the medicalRecords
	 */
	public List<MedicalRecord> getMedicalrecords() {
		return medicalrecords;
	}

	/**
	 * @param medicalRecords - the list medicalRecords to set
	 */
	public void setMedicalRecords(List<MedicalRecord> medicalrecords) {
		this.medicalrecords = medicalrecords;
	}

	@PostConstruct
	public void readDataFile() throws IOException {

		// read json file data to String
		byte[] jsonData = Files.readAllBytes(Paths.get("./src/main/resources/data.json"));

		// create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();

		DataSafetyNet dataJson = objectMapper.readValue(jsonData, DataSafetyNet.class);

		this.setPersons(dataJson.getPersons());
		this.setFirestations(dataJson.getFirestations());
		this.setMedicalRecords(dataJson.getMedicalrecords());

//		return dataJson;

	}
}
