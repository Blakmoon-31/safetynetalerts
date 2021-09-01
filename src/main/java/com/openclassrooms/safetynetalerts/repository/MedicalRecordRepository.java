package com.openclassrooms.safetynetalerts.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetynetalerts.model.DataSafetyNet;
import com.openclassrooms.safetynetalerts.model.MedicalRecord;

@Repository
public class MedicalRecordRepository {

	@Autowired
	private DataSafetyNet datasafetyNet;

	public List<MedicalRecord> findAll() {
		return datasafetyNet.getMedicalrecords();
	}

}
