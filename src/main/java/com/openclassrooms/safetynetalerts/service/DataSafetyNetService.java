package com.openclassrooms.safetynetalerts.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	private static Logger logger = LoggerFactory.getLogger(DataSafetyNetService.class);

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
		logger.debug("Persons list for a station obtained");
		return listOfPersonsForStationNumber;

	}

	public List<String> getPhoneAlertListForAFireStation(String firestation) {
		logger.debug("Phone alert list for a station finding");
		return dataSafetyNetRepository.findPhoneListByFireStation(firestation);
	}

	public List<String> getCommunityEmailListForACity(String city) {
		logger.debug("Community email list for a city finding");
		return dataSafetyNetRepository.findEmailListByCity(city);
	}

}
