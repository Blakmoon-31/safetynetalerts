package com.openclassrooms.safetynetalerts.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetynetalerts.model.DataSafetyNet;
import com.openclassrooms.safetynetalerts.model.MedicalRecord;

@Repository
public class MedicalRecordRepository {

	@Autowired
	private DataSafetyNet dataSafetyNet;

	public List<MedicalRecord> findAll() {
		return dataSafetyNet.getMedicalrecords();
	}

	public MedicalRecord findByFirstNameAndLastName(String firstName, String lastName) {
		List<MedicalRecord> medicalRecordsList = dataSafetyNet.getMedicalrecords();
		MedicalRecord resultMedicalRecord = null;

		for (MedicalRecord m : medicalRecordsList) {
			if (m.getFirstName().equals(firstName) && m.getLastName().equals(lastName)) {
				resultMedicalRecord = m;
			}
		}

		// TODO Method to find and return the person
		return resultMedicalRecord;
	}

	public MedicalRecord save(MedicalRecord medicalRecord) {
		// TODO Add medical record in data.json
		List<MedicalRecord> medicalRecordsInData = dataSafetyNet.getMedicalrecords();
		boolean exist = false;
		// If medical record's first and last names already in list, update
		for (int i = 0; i < medicalRecordsInData.size() && !exist; i++) {
			if (medicalRecordsInData.get(i).getFirstName().equals(medicalRecord.getFirstName())
					&& medicalRecordsInData.get(i).getLastName().equals(medicalRecord.getLastName())) {

				medicalRecordsInData.get(i).setBirthdate(medicalRecord.getBirthdate());
				medicalRecordsInData.get(i).setMedications(medicalRecord.getMedications());
				medicalRecordsInData.get(i).setAllergies(medicalRecord.getAllergies());

				exist = true;
			}
		}
		// If medical record not in list, add
		if (exist == false) {
			medicalRecordsInData.add(medicalRecord);
		}
		// Update the global data
		dataSafetyNet.setMedicalRecords(medicalRecordsInData);

		// manage possible error in return ?
		return medicalRecord;
	}

	public void deleteByFirstNameAndLastName(String firstName, String lastName) {
		// TODO Delete medical record in data.json
		List<MedicalRecord> medicalRecordsInData = dataSafetyNet.getMedicalrecords();
		boolean deleted = false;
		// If medical record's first and last names exist in list, remove it from list
		for (int i = 0; i < medicalRecordsInData.size() && !deleted; i++) {
			if (medicalRecordsInData.get(i).getFirstName().equals(firstName)
					&& medicalRecordsInData.get(i).getLastName().equals(lastName)) {
				medicalRecordsInData.remove(i);
				dataSafetyNet.setMedicalRecords(medicalRecordsInData);
				deleted = true;
			}
		}
		// manage possible error in return ?

	}
}
