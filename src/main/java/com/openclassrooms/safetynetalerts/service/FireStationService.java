package com.openclassrooms.safetynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetynetalerts.model.FireStation;
import com.openclassrooms.safetynetalerts.repository.FireStationRepository;

@Service
public class FireStationService {

	@Autowired
	private FireStationRepository fireStationRepository;

	public List<FireStation> getFireStations() {
		return fireStationRepository.findAll();
	}

	public FireStation getFireStationByAdress(String adress) {
		return fireStationRepository.findByAddress(adress);
	}

	public List<FireStation> getFireStationByFireStation(String number) {
		return fireStationRepository.findByStation(number);
	}

	public FireStation saveFireStation(FireStation fireStation) {
		return fireStationRepository.save(fireStation);
	}

	public void deleteFireStationByAdress(String address) {
		fireStationRepository.deleteByAddress(address);

	}

	public void deleteFireStationByFireStation(String number) {
		fireStationRepository.deleteByFireStation(number);

	}
}
