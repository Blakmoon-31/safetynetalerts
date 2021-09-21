package com.openclassrooms.safetynetalerts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.safetynetalerts.configuration.SwaggerConfiguration;
import com.openclassrooms.safetynetalerts.dto.ChildAlertDto;
import com.openclassrooms.safetynetalerts.dto.FireDto;
import com.openclassrooms.safetynetalerts.dto.FloodDto;
import com.openclassrooms.safetynetalerts.dto.PersonInfoDto;
import com.openclassrooms.safetynetalerts.dto.StationDto;
import com.openclassrooms.safetynetalerts.service.DataSafetyNetService;
import com.openclassrooms.safetynetalerts.service.MapDtoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { SwaggerConfiguration.SAFETYNET_ALERTS_TAG })
public class DataSafetyNetController {

	@Autowired
	private DataSafetyNetService dataSafetyNetService;

	@Autowired
	private MapDtoService mapDtoService;

	private static Logger logger = LoggerFactory.getLogger(DataSafetyNetController.class);

	@ApiOperation(value = "To obtain a list of persons (first and last name, phone) and the count of adults and children covered by the given station number")
	@GetMapping("/firestation")
	public StationDto getPersonsForAStation(@RequestParam("stationNumber") String stationNumber) {
		logger.info("Persons list for a station requested");
		return mapDtoService.getListForAStation(stationNumber);
	}

	@ApiOperation(value = "To obtain a list of phone numbers of persons living at the addresses covered by the given station number")
	@GetMapping("/phoneAlert")
	public List<String> getPhoneAlertListForAFireStation(@RequestParam("firestation") String firestation) {
		logger.info("Phone alert list requested");
		return dataSafetyNetService.getPhoneAlertListForAFireStation(firestation);
	}

	@ApiOperation(value = "To obtain an email list of all persons living in the given city")
	@GetMapping("/communityEmail")
	public List<String> getCommunityEmailListForACity(@RequestParam("city") String city) {
		logger.info("Community email list requested");
		return dataSafetyNetService.getCommunityEmailListForACity(city);
	}

	@ApiOperation(value = "To obtain a list of persons living at the given adress (first and last name, phone, age, medications and allergies) and the fire station number serving this address")
	@GetMapping("/fire")
	public List<FireDto> getFireListForAnAddress(@RequestParam("address") String address) {
		logger.info("Fire list requested");
		return mapDtoService.getFireListForAnAddress(address);
	}

	@ApiOperation(value = "To obtain a list persons (first and last name, phone, age, medications and allergies), grouped by address, covered by the given list of station's number")
	@GetMapping("/flood/stations")
	public List<FloodDto> getFloodListForAListOfStations(@RequestParam("stations") List<String> stationList) {
		logger.info("Flood list requested");
		return mapDtoService.getFloodListForAListOfStations(stationList);
	}

	@ApiOperation(value = "To obtain a list of person informations (first and last name, address, age, email, medications and allergies) having the given first and/or last name")
	@GetMapping("/personInfo")
	public List<PersonInfoDto> getPersonInfoByFirstNameAndLastName(
			@RequestParam(value = "firstName", required = false) String firstName,
			@RequestParam(value = "lastName", required = false) String lastName) {
		logger.info("Person info list requested");
		return mapDtoService.getPersonInfoByFirstNameAndLastName(firstName, lastName);
	}

	@ApiOperation(value = "To obtain a list of children (first and last name, age) and the list of other members living at the given address")
	@GetMapping("/childAlert")
	public ChildAlertDto getChildAlertListForAnAddress(@RequestParam("address") String address) {
		logger.info("Child alert list requested");
		return mapDtoService.getChildAlertListForAnAddress(address);
	}

}
