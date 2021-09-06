package com.openclassrooms.safetynetalerts.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetynetalerts.model.MedicalRecord;
import com.openclassrooms.safetynetalerts.repository.MedicalRecordRepository;

@Service
public class MedicalRecordService {

	@Autowired
	private MedicalRecordRepository medicalRecordRepository;

	public List<MedicalRecord> getMedicalrecords() {
		return medicalRecordRepository.findAll();
	}

	public MedicalRecord getMedicalRecord(String firstName, String lastName) {
		return medicalRecordRepository.findByFirstNameAndLastName(firstName, lastName);
	}

	public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
		return medicalRecordRepository.save(medicalRecord);
	}

	public void deleteMedicalRecord(String firstName, String lastName) {
		medicalRecordRepository.deleteByFirstNameAndLastName(firstName, lastName);

	}

	public int calculateAge(LocalDate birthdate) {
		LocalDate today = LocalDate.now();
		int age = Period.between(birthdate, today).getYears();

		return age;

	}
}
