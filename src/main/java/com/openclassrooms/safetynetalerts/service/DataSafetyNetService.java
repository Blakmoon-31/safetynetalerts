package com.openclassrooms.safetynetalerts.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.openclassrooms.safetynetalerts.model.FireStation;
import com.openclassrooms.safetynetalerts.model.Person;
import com.openclassrooms.safetynetalerts.repository.DataSafetyNetRepository;

@Service
public class DataSafetyNetService {

	@Autowired
	private DataSafetyNetRepository dataRepository;

	public List<Person> getPersonsForAStation(
			@RequestParam(value = "stationNumber", required = false) List<String> stationList) {

		List<Person> listOfPersons = dataRepository.getPersons();
		List<FireStation> listOfStations = dataRepository.getFireStations();

		List<Person> listOfPersonsForStationNumber = new ArrayList<Person>();

		for (FireStation lfs : listOfStations) {
			for (String stationNumber : stationList) {
				if (lfs.getStation().equals(stationNumber)) {
					for (Person lp : listOfPersons) {
						if (lp.getAddress().equals(lfs.getAddress())) {
							listOfPersonsForStationNumber.add(lp);
						}
					}
				}
			}
		}

		return listOfPersonsForStationNumber;

	}

}
