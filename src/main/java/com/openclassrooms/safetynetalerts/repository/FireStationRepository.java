package com.openclassrooms.safetynetalerts.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetynetalerts.model.DataSafetyNet;
import com.openclassrooms.safetynetalerts.model.FireStation;

@Repository
public class FireStationRepository {

	@Autowired
	private DataSafetyNet dataSafetyNet;

	public List<FireStation> findAll() {
		return dataSafetyNet.getFirestations();
	}

}
