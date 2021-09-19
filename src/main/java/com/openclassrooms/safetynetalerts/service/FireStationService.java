package com.openclassrooms.safetynetalerts.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetynetalerts.model.FireStation;
import com.openclassrooms.safetynetalerts.repository.FireStationRepository;

@Service
public class FireStationService {

	@Autowired
	private FireStationRepository fireStationRepository;

	private static Logger logger = LoggerFactory.getLogger(FireStationService.class);

	public List<FireStation> getFireStations() {
		logger.debug("Fire stations list finding");
		return fireStationRepository.findAll();
	}

	public FireStation getFireStationByAdress(String adress) {
		logger.debug("Fire stations list by address finding");
		return fireStationRepository.findByAddress(adress);
	}

	public List<FireStation> getFireStationByFireStation(String number) {
		logger.debug("Fire stations list by station number finding");
		return fireStationRepository.findByStation(number);
	}

	public FireStation saveFireStation(FireStation fireStation) {
		logger.debug("Fire station mapping to save");
		return fireStationRepository.save(fireStation);
	}

	public void deleteFireStationByAdress(String address) {
		logger.debug("Fire station mapping for an address to delete");
		fireStationRepository.deleteByAddress(address);
	}

	public void deleteFireStationByFireStation(String number) {
		logger.debug("Fire station mapping for a station to delete");
		fireStationRepository.deleteByFireStation(number);
	}
}
