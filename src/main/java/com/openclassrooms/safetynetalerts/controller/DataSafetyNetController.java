package com.openclassrooms.safetynetalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.safetynetalerts.model.Person;
import com.openclassrooms.safetynetalerts.service.DataSafetyNetService;

@RestController
public class DataSafetyNetController {

	@Autowired
	private DataSafetyNetService personservice;

	@GetMapping("/firestation")
	public List<Person> getPersonsForAStation(
			@RequestParam(value = "stationNumber", required = false) List<String> stationList) {

		return personservice.getPersonsForAStation(stationList);
	}
}
