package com.openclassrooms.safetynetalerts.repository;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.openclassrooms.safetynetalerts.model.DataSafetyNet;
import com.openclassrooms.safetynetalerts.model.FireStation;
import com.openclassrooms.safetynetalerts.model.MedicalRecord;
import com.openclassrooms.safetynetalerts.model.Person;

@Repository
public class DataSafetyNetRepository {

	@Autowired
	private DataSafetyNet dataSafetyNet;

	@Autowired
	private FireStationRepository fireStationRepository;

	public List<Person> getPersons() {
		return dataSafetyNet.getPersons();

	}

	public List<FireStation> getFireStations() {
		return dataSafetyNet.getFirestations();

	}

	public List<MedicalRecord> getMedicalRecords() {
		return dataSafetyNet.getMedicalrecords();

	}

	public List<String> findPhoneListByFireStation(String firestation) {
		List<FireStation> fireStationsList = fireStationRepository.findByStation(firestation);
		List<Person> personsList = dataSafetyNet.getPersons();

		List<String> phoneList = new ArrayList<String>();

		for (FireStation f : fireStationsList) {
			String address = f.getAddress();
			for (Person p : personsList) {
				if (address.equals(p.getAddress()) && phoneList.contains(p.getPhone()) == false) {
					phoneList.add(p.getPhone());
				}
			}
		}

		return phoneList;
	}

	public List<String> findEmailListByCity(String city) {
		List<Person> personsList = dataSafetyNet.getPersons();
		List<String> emailList = new ArrayList<String>();

		for (Person p : personsList) {
			if (p.getCity().equals(city) && emailList.contains(p.getEmail()) == false) {
				emailList.add(p.getEmail());
			}
		}

		return emailList;
	}

	public void writeDataFileTEST() throws FileNotFoundException, IOException {
		JsonGenerator jsonGenerator = new JsonFactory()
				.createGenerator(new FileOutputStream("./src/main/resources/test_output.json"));
		jsonGenerator.setPrettyPrinter(new DefaultPrettyPrinter());

		jsonGenerator.writeStartObject();

		jsonGenerator.writeArrayFieldStart("persons");
		for (Person person : dataSafetyNet.getPersons())
			jsonGenerator.writeObject(person.toString());
		jsonGenerator.writeEndArray();

		jsonGenerator.writeArrayFieldStart("firestations");
		for (FireStation fireStation : dataSafetyNet.getFirestations())
			jsonGenerator.writeObject(fireStation.toString());
		jsonGenerator.writeEndArray();

		jsonGenerator.writeArrayFieldStart("medicalrecords");
		for (MedicalRecord medicalRecord : dataSafetyNet.getMedicalrecords())
			jsonGenerator.writeObject(medicalRecord.toString());
		jsonGenerator.writeEndArray();

		jsonGenerator.writeEndObject();

		jsonGenerator.flush();
		jsonGenerator.close();
	}
}
