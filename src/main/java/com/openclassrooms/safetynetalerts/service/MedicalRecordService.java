package com.openclassrooms.safetynetalerts.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetynetalerts.model.MedicalRecord;
import com.openclassrooms.safetynetalerts.repository.MedicalRecordRepository;

@Service
public class MedicalRecordService {

	@Autowired
	private MedicalRecordRepository medicalRecordRepository;

	private static Logger logger = LoggerFactory.getLogger(MedicalRecordService.class);

	public List<MedicalRecord> getMedicalRecords() {
		logger.debug("Medical records list to find");
		return medicalRecordRepository.findAll();
	}

	public MedicalRecord getMedicalRecord(String firstName, String lastName) {
		logger.debug("Medical record of a person to find");
		return medicalRecordRepository.findByFirstNameAndLastName(firstName, lastName);
	}

	public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
		logger.debug("Medical record of a person to save");
		return medicalRecordRepository.save(medicalRecord);
	}

	public void deleteMedicalRecord(String firstName, String lastName) {
		logger.debug("Medical record of a person to delete");
		medicalRecordRepository.deleteByFirstNameAndLastName(firstName, lastName);

	}

	public int calculateAge(LocalDate birthdate) {
		LocalDate today = LocalDate.now();
		int age = Period.between(birthdate, today).getYears();

		logger.debug("Age of a person calculated");
		return age;

	}
}
