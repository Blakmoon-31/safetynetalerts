package com.openclassrooms.safetynetalerts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.safetynetalerts.dto.ChildAlertDto;
import com.openclassrooms.safetynetalerts.dto.FireDto;
import com.openclassrooms.safetynetalerts.dto.FloodDto;
import com.openclassrooms.safetynetalerts.dto.PersonInfoDto;
import com.openclassrooms.safetynetalerts.dto.StationDto;
import com.openclassrooms.safetynetalerts.service.DataSafetyNetService;
import com.openclassrooms.safetynetalerts.service.MapDtoService;

@RestController
public class DataSafetyNetController {

	@Autowired
	private DataSafetyNetService dataSafetyNetService;

	@Autowired
	private MapDtoService mapDtoService;

	private static Logger logger = LoggerFactory.getLogger(DataSafetyNetController.class);

	@GetMapping("/firestation")
	public StationDto getPersonsForAStation(@RequestParam("stationNumber") String stationNumber) {
		logger.info("Persons list for a station requested");
		return mapDtoService.getListForAStation(stationNumber);
	}

	@GetMapping("/phoneAlert")
	public List<String> getPhoneAlertListForAFireStation(@RequestParam("firestation") String firestation) {
		logger.info("Phone alert list requested");
		return dataSafetyNetService.getPhoneAlertListForAFireStation(firestation);
	}

	@GetMapping("/communityEmail")
	public List<String> getCommunityEmailListForACity(@RequestParam("city") String city) {
		logger.info("Community email list requested");
		return dataSafetyNetService.getCommunityEmailListForACity(city);
	}

	@GetMapping("/fire")
	public List<FireDto> getFireListForAnAddress(@RequestParam("address") String address) {
		logger.info("Fire list requested");
		return mapDtoService.getFireListForAnAddress(address);
	}

	@GetMapping("/flood/stations")
	public List<FloodDto> getFloodListForAListOfStations(@RequestParam("stations") List<String> stationList) {
		logger.info("Flood list requested");
		return mapDtoService.getFloodListForAListOfStations(stationList);
	}

	@GetMapping("/personInfo")
	public List<PersonInfoDto> getPersonInfoByFirstNameAndLastName(
			@RequestParam(value = "firstName", required = false) String firstName,
			@RequestParam(value = "lastName", required = false) String lastName) {
		logger.info("Person info list requested");
		return mapDtoService.getPersonInfoByFirstNameAndLastName(firstName, lastName);
	}

	@GetMapping("/childAlert")
	public ChildAlertDto getChildAlertListForAnAddress(@RequestParam("address") String address) {
		logger.info("Child alert list requested");
		return mapDtoService.getChildAlertListForAnAddress(address);
	}

}
