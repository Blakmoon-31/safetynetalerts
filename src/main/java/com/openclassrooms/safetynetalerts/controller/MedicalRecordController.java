package com.openclassrooms.safetynetalerts.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

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

import com.openclassrooms.safetynetalerts.model.MedicalRecord;
import com.openclassrooms.safetynetalerts.service.MedicalRecordService;

@RestController
public class MedicalRecordController {

	@Autowired
	private MedicalRecordService medicalRecordService;

	/**
	 * Read - Get all medical records
	 * 
	 * @return - An Iterable object of MedicalRecord full filled
	 */
	@GetMapping("/medicalrecords")
	public List<MedicalRecord> getMedicalrecords() {

		return medicalRecordService.getMedicalRecords();
	}

	/**
	 * Read - Get one medical record by its id (firstName + lastName)
	 * 
	 * @param firstName - The firstName of the concerned person
	 * @param lastName  - The lastName of the concerned person
	 * @return - An object MedicalRecord full filled
	 */
	@GetMapping("/medicalrecord/{firstName}/{lastName}")
	public MedicalRecord getMedicalRecord(@PathVariable("firstName") String firstName,
			@PathVariable("lastName") String lastName) {
		return medicalRecordService.getMedicalRecord(firstName, lastName);
	}

	/**
	 * Create - Add a new medical record
	 * 
	 * @param medicalRecord - An object MedicalRecord
	 * @return - The HTTP code "201" and URI if object created, HTTP code "204" if
	 *         not
	 */
	@PostMapping("/medicalrecord")
	public ResponseEntity<Void> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {

		MedicalRecord medicalRecordCreated = medicalRecordService.saveMedicalRecord(medicalRecord);

		if (medicalRecordCreated == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{firstName}/{lastName}")
				.buildAndExpand(medicalRecordCreated.getFirstName(), medicalRecordCreated.getLastName()).toUri();

		return ResponseEntity.created(location).build();

	}

	/**
	 * Update - Update an existing medical record
	 * 
	 * @param firstName     - The firstName of the concerned person
	 * @param lastName      - The lastName of the concerned person
	 * @param medicalRecord - An object MedicalRecord
	 * @return - The MedicalRecord object updated
	 */
	@PutMapping("/medicalrecord/{firstName}/{lastName}")
	public MedicalRecord updateMedicalRecord(@PathVariable("firstName") String firstName,
			@PathVariable("lastName") String lastName, @RequestBody MedicalRecord medicalRecord) {

		MedicalRecord currentMedicalRecord = medicalRecordService.getMedicalRecord(firstName, lastName);
		if (currentMedicalRecord != null) {

			LocalDate birthdate = medicalRecord.getBirthdate();
			if (birthdate != null) {
				currentMedicalRecord.setBirthdate(birthdate);
			}

			List<String> medications = medicalRecord.getMedications();
			if (medications != null) {
				currentMedicalRecord.setMedications(medications);
			}

			List<String> allergies = medicalRecord.getAllergies();
			if (allergies != null) {
				currentMedicalRecord.setAllergies(allergies);
			}
			medicalRecordService.saveMedicalRecord(currentMedicalRecord);
			return currentMedicalRecord;
		} else {
			return null;
		}
	}

	/**
	 * Delete a medical record by its id
	 * 
	 * @param firstName - The firstName of the concerned person
	 * @param lastName  - The lastName of the concerned person
	 */
	@DeleteMapping("/medicalrecord/{firstName}/{lastName}")
	public void deleteMedicalRecord(@PathVariable("firstName") String firstName,
			@PathVariable("lastName") String lastName) {
		medicalRecordService.deleteMedicalRecord(firstName, lastName);
	}
}
