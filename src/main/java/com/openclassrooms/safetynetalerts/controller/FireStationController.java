package com.openclassrooms.safetynetalerts.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.openclassrooms.safetynetalerts.model.FireStation;
import com.openclassrooms.safetynetalerts.service.FireStationService;

@RestController
public class FireStationController {

	@Autowired
	private FireStationService fireStationService;

	private static Logger logger = LoggerFactory.getLogger(PersonController.class);

	/**
	 * Read - Get all fire stations/addresses mapping
	 * 
	 * @return - An Iterable object of FireStation full filled
	 */
	@GetMapping("/firestations")
	public List<FireStation> getFireStations() {
		return fireStationService.getFireStations();
	}

	/**
	 * Read - Get one address mapping
	 * 
	 * @param adress - The address of the mapping
	 * @return - An object FireStation full filled
	 */
	@GetMapping("/firestation/address/{adress}")
	public FireStation getFireStationByAdress(@PathVariable("adress") String adress) {
		return fireStationService.getFireStationByAdress(adress);
	}

	/**
	 * Read - Get one fire station mapping
	 * 
	 * @param number - The number of the station
	 * @return - An object FireStation full filled
	 */
	@GetMapping("/firestation/station/{number}")
	public List<FireStation> getFireStationByFireStation(@PathVariable("number") String number) {
		return fireStationService.getFireStationByFireStation(number);
	}

	/**
	 * Create - Add a new fire station/address mapping
	 * 
	 * @param person - An object FireStation
	 * @return - The HTTP code "201" and URI if object created, HTTP code "204" if
	 *         not
	 */
	@PostMapping("/firestation")
	public ResponseEntity<Void> createFireStation(@RequestBody FireStation fireStation) {

		if (fireStation.getAddress() == null || fireStation.getStation() == null) {
			logger.error("Fire station mapping not created : no content in request body");
			return ResponseEntity.noContent().build();

		}
		FireStation fireStationCreated = fireStationService.saveFireStation(fireStation);

		if (fireStationCreated.getAddress() == null) {
			logger.error("Fire station mapping not created");
			return ResponseEntity.noContent().build();

		}

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/address/{address}")
				.buildAndExpand(fireStationCreated.getAddress()).toUri();
		logger.info("Fire station mapping created");
		return ResponseEntity.created(location).build();
	}

	/**
	 * Update - Update the fire station number of an address
	 * 
	 * @param address     - The address to update
	 * @param fireStation - An object FireStation
	 * @return - The FireStation object updated
	 */
	@PutMapping("/firestation/{address}")
	public FireStation updateFireStation(@PathVariable("address") String address,
			@RequestBody FireStation fireStation) {
		FireStation currentAddress = fireStationService.getFireStationByAdress(address);
		if (currentAddress != null) {

			String station = fireStation.getStation();
			if (station != null) {
				currentAddress.setStation(station);
			}
			fireStationService.saveFireStation(currentAddress);
			return currentAddress;
		} else {
			return null;
		}
	}

	/**
	 * Delete the mapping of an address
	 * 
	 * @param address - An address
	 */
	@DeleteMapping("/firestation/address/{address}")
	public void deleteFireStationByAdress(@PathVariable("address") String address) {
		fireStationService.deleteFireStationByAdress(address);
	}

	/**
	 * Delete the mapping of a fire station
	 * 
	 * @param number - A station number
	 */
	@DeleteMapping("/firestation/station/{number}")
	public void deleteFireStationByFireStation(@PathVariable("number") String number) {
		fireStationService.deleteFireStationByFireStation(number);
	}

}
