package com.openclassrooms.safetynetalerts.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetynetalerts.model.DataSafetyNet;
import com.openclassrooms.safetynetalerts.model.FireStation;
import com.openclassrooms.safetynetalerts.model.MedicalRecord;
import com.openclassrooms.safetynetalerts.model.Person;

@Repository
public class DataSafetyNetRepository {

	@Autowired
	private DataSafetyNet dataSafetyNet;

	public List<Person> getPersons() {
		return dataSafetyNet.getPersons();

	}

	public List<FireStation> getFireStations() {
		return dataSafetyNet.getFirestations();

	}

	public List<MedicalRecord> getMedicalRecords() {
		return dataSafetyNet.getMedicalrecords();

	}

}
