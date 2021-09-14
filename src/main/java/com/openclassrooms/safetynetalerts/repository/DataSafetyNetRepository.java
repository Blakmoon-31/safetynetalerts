package com.openclassrooms.safetynetalerts.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetynetalerts.model.DataSafetyNet;
import com.openclassrooms.safetynetalerts.model.FireStation;
import com.openclassrooms.safetynetalerts.model.Person;

@Repository
public class DataSafetyNetRepository {

	@Autowired
	private DataSafetyNet dataSafetyNet;

	@Autowired
	private FireStationRepository fireStationRepository;

	public List<Person> getPersons() {
		return dataSafetyNet.getPersons();

	}

	public List<String> findPhoneListByFireStation(String firestation) {
		List<FireStation> fireStationsList = fireStationRepository.findByStation(firestation);
		List<Person> personsList = dataSafetyNet.getPersons();

		List<String> phoneList = new ArrayList<String>();

		for (FireStation f : fireStationsList) {
			String address = f.getAddress();
			for (Person p : personsList) {
				if (address.equals(p.getAddress()) && phoneList.contains(p.getPhone()) == false) {
					phoneList.add(p.getPhone());
				}
			}
		}

		return phoneList;
	}

	public List<String> findEmailListByCity(String city) {
		List<Person> personsList = dataSafetyNet.getPersons();
		List<String> emailList = new ArrayList<String>();

		for (Person p : personsList) {
			if (p.getCity().equals(city) && emailList.contains(p.getEmail()) == false) {
				emailList.add(p.getEmail());
			}
		}

		return emailList;
	}

}
