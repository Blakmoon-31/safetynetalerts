package com.openclassrooms.safetynetalerts.controller;

import java.net.URI;
import java.time.LocalDate;
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

import com.openclassrooms.safetynetalerts.configuration.SwaggerConfiguration;
import com.openclassrooms.safetynetalerts.model.MedicalRecord;
import com.openclassrooms.safetynetalerts.service.MedicalRecordService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { SwaggerConfiguration.MEDICAL_RECORDS_TAG })
public class MedicalRecordController {

	@Autowired
	private MedicalRecordService medicalRecordService;

	private static Logger logger = LoggerFactory.getLogger(MedicalRecordController.class);

	/**
	 * Read - Get all medical records
	 * 
	 * @return - An Iterable object of MedicalRecord full filled
	 */
	@ApiOperation(value = "Retrieves medical records list")
	@GetMapping("/medicalrecords")
	public List<MedicalRecord> getMedicalrecords() {
		logger.info("Medical records list requested");
		return medicalRecordService.getMedicalRecords();
	}

	/**
	 * Read - Get one medical record by its id (firstName + lastName)
	 * 
	 * @param firstName - The firstName of the concerned person
	 * @param lastName  - The lastName of the concerned person
	 * @return - An object MedicalRecord full filled
	 */
	@ApiOperation(value = "Retrieves a medical record by first and last name")
	@GetMapping("/medicalrecord/{firstName}/{lastName}")
	public MedicalRecord getMedicalRecord(@PathVariable("firstName") String firstName,
			@PathVariable("lastName") String lastName) {
		logger.info("Medical record for a person list requested");
		return medicalRecordService.getMedicalRecord(firstName, lastName);
	}

	/**
	 * Create - Add a new medical record
	 * 
	 * @param medicalRecord - An object MedicalRecord
	 * @return - The HTTP code "201" and URI if object created, HTTP code "204" if
	 *         not
	 */
	@ApiOperation(value = "Adds a medical record")
	@PostMapping("/medicalrecord")
	public ResponseEntity<Void> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {

		MedicalRecord medicalRecordCreated = medicalRecordService.saveMedicalRecord(medicalRecord);

		if (medicalRecordCreated == null)
			return ResponseEntity.noContent().build();
		logger.info("Medical record created");
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
	@ApiOperation(value = "Updates a medical record by first and last name")
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
			logger.info("Medical record of a person updated");
			return currentMedicalRecord;
		} else {
			logger.info("Medical record not updated");
			return null;
		}
	}

	/**
	 * Delete a medical record by its id
	 * 
	 * @param firstName - The firstName of the concerned person
	 * @param lastName  - The lastName of the concerned person
	 */
	@ApiOperation(value = "Delete a medical record by first and last name")
	@DeleteMapping("/medicalrecord/{firstName}/{lastName}")
	public void deleteMedicalRecord(@PathVariable(value = "firstName", required = true) String firstName,
			@PathVariable(value = "lastName", required = true) String lastName) {
		logger.info("Medical record of a person deleted");
		medicalRecordService.deleteMedicalRecord(firstName, lastName);
	}
}
