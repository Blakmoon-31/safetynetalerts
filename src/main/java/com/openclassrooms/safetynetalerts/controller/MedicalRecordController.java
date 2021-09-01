package com.openclassrooms.safetynetalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.safetynetalerts.model.MedicalRecord;
import com.openclassrooms.safetynetalerts.service.MedicalRecordService;

@RestController
public class MedicalRecordController {

	@Autowired
	private MedicalRecordService medicalRecordService;

	@GetMapping("/medicalrecords")
	public List<MedicalRecord> getMedicalrecords() {

		return medicalRecordService.getMedicalrecord();
	}
}
