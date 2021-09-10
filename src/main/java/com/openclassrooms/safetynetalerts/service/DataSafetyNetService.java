package com.openclassrooms.safetynetalerts.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.openclassrooms.safetynetalerts.model.FireStation;
import com.openclassrooms.safetynetalerts.model.Person;
import com.openclassrooms.safetynetalerts.repository.DataSafetyNetRepository;
import com.openclassrooms.safetynetalerts.repository.FireStationRepository;

@Service
public class DataSafetyNetService {

	@Autowired
	private DataSafetyNetRepository dataSafetyNetRepository;

	@Autowired
	private FireStationRepository fireStationRepository;

	public List<Person> getPersonsForAListOfStation(
			@RequestParam(value = "stationNumber", required = false) List<String> stationList) {

		List<Person> listOfPersons = dataSafetyNetRepository.getPersons();
		List<FireStation> listOfStations = dataSafetyNetRepository.getFireStations();

		List<Person> listOfPersonsForStationList = new ArrayList<Person>();

		for (FireStation lfs : listOfStations) {
			for (String stationNumber : stationList) {
				if (lfs.getStation().equals(stationNumber)) {
					for (Person lp : listOfPersons) {
						if (lp.getAddress().equals(lfs.getAddress())) {
							listOfPersonsForStationList.add(lp);
						}
					}
				}
			}
		}

		return listOfPersonsForStationList;

	}

	public List<Person> getPersonsForAStation(String station) {

		List<Person> listOfPersons = dataSafetyNetRepository.getPersons();
		List<FireStation> listOfStations = fireStationRepository.findByStation(station);

		List<Person> listOfPersonsForStationNumber = new ArrayList<Person>();

		for (FireStation lfs : listOfStations) {
			for (Person lp : listOfPersons) {
				if (lp.getAddress().equals(lfs.getAddress())) {
					listOfPersonsForStationNumber.add(lp);
				}
			}
		}

		return listOfPersonsForStationNumber;

	}

	public List<String> getPhoneAlertListForAFireStation(String firestation) {
		return dataSafetyNetRepository.findPhoneListByFireStation(firestation);
	}

	public List<String> getCommunityEmailListForACity(String city) {
		return dataSafetyNetRepository.findEmailListByCity(city);
	}

}
