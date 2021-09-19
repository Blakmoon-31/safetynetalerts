package com.openclassrooms.safetynetalerts.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetynetalerts.model.DataSafetyNet;
import com.openclassrooms.safetynetalerts.model.MedicalRecord;

@Repository
public class MedicalRecordRepository {

	@Autowired
	private DataSafetyNet dataSafetyNet;

	private static Logger logger = LoggerFactory.getLogger(MedicalRecordRepository.class);

	public List<MedicalRecord> findAll() {
		logger.debug("Medical records list found");
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

		logger.debug("Medical record of a person found");
		return resultMedicalRecord;
	}

	public MedicalRecord save(MedicalRecord medicalRecord) {

		List<MedicalRecord> medicalRecordsInData = dataSafetyNet.getMedicalrecords();
		boolean exist = false;
		// If medical record's first and last names already in list, update
		for (int i = 0; i < medicalRecordsInData.size() && !exist; i++) {
			if (medicalRecordsInData.get(i).getFirstName().equals(medicalRecord.getFirstName())
					&& medicalRecordsInData.get(i).getLastName().equals(medicalRecord.getLastName())) {

				medicalRecordsInData.get(i).setBirthdate(medicalRecord.getBirthdate());
				medicalRecordsInData.get(i).setMedications(medicalRecord.getMedications());
				medicalRecordsInData.get(i).setAllergies(medicalRecord.getAllergies());
				logger.debug("Medical record updated");
				exist = true;
			}
		}
		// If medical record not in list, add
		if (exist == false) {
			medicalRecordsInData.add(medicalRecord);
			logger.debug("Medical record created");
		}
		// Update the global data
		dataSafetyNet.setMedicalRecords(medicalRecordsInData);

		return medicalRecord;
	}

	public void deleteByFirstNameAndLastName(String firstName, String lastName) {

		List<MedicalRecord> medicalRecordsInData = dataSafetyNet.getMedicalrecords();
		boolean deleted = false;
		// If medical record's first and last names exist in list, remove it from list
		for (int i = 0; i < medicalRecordsInData.size() && !deleted; i++) {
			if (medicalRecordsInData.get(i).getFirstName().equals(firstName)
					&& medicalRecordsInData.get(i).getLastName().equals(lastName)) {
				medicalRecordsInData.remove(i);
				dataSafetyNet.setMedicalRecords(medicalRecordsInData);
				deleted = true;
				logger.debug("Medical record deleted");
			}
		}

	}
}
