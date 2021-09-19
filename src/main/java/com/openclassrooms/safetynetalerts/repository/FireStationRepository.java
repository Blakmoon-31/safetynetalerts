package com.openclassrooms.safetynetalerts.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetynetalerts.model.DataSafetyNet;
import com.openclassrooms.safetynetalerts.model.FireStation;

@Repository
public class FireStationRepository {

	@Autowired
	private DataSafetyNet dataSafetyNet;

	private static Logger logger = LoggerFactory.getLogger(FireStationRepository.class);

	public List<FireStation> findAll() {
		logger.debug("Fire station list found");
		return dataSafetyNet.getFirestations();
	}

	public FireStation findByAddress(String address) {
		List<FireStation> stationsList = dataSafetyNet.getFirestations();
		FireStation resultStation = null;

		for (FireStation f : stationsList) {
			if (f.getAddress().equals(address)) {
				resultStation = f;
			}
		}
		logger.debug("Fire station mapping for an address found");
		return resultStation;
	}

	public List<FireStation> findByStation(String number) {
		List<FireStation> stationsList = dataSafetyNet.getFirestations();
		List<FireStation> resultStationList = new ArrayList<FireStation>();

		for (FireStation f : stationsList) {
			if (f.getStation().equals(number)) {
				resultStationList.add(f);
			}
		}
		logger.debug("Fire station mapping for a station found");
		return resultStationList;

	}

	public FireStation save(FireStation fireStation) {

		List<FireStation> stationsInData = dataSafetyNet.getFirestations();
		boolean exist = false;
		// If address already in list, update the station
		for (int i = 0; i < stationsInData.size() && !exist; i++) {
			if (stationsInData.get(i).getAddress().equals(fireStation.getAddress())) {

				stationsInData.get(i).setStation(fireStation.getStation());
				exist = true;
				logger.debug("Fire station mapping updated");
			}
		}
		// If address not in list, add it
		if (exist == false) {
			stationsInData.add(fireStation);
			logger.debug("Fire station mapping created");
		}
		// Update the global data
		dataSafetyNet.setFirestations(stationsInData);

		// manage possible error in return ?
		return fireStation;
	}

	public void deleteByAddress(String address) {

		List<FireStation> stationsInData = dataSafetyNet.getFirestations();
		boolean deleted = false;
		// If address exists in list, remove it from list
		for (int i = 0; i < stationsInData.size() && !deleted; i++) {
			if (stationsInData.get(i).getAddress().equals(address)) {
				stationsInData.remove(i);
				dataSafetyNet.setFirestations(stationsInData);
				deleted = true;
				logger.debug("Fire station mapping for an address deleted");
			}
		}

	}

	public void deleteByFireStation(String number) {

		List<FireStation> stationsInData = dataSafetyNet.getFirestations();
		// If fire station exists in list, remove every mapping from list
		for (int i = stationsInData.size() - 1; i >= 0; i--) {
			if (stationsInData.get(i).getStation().equals(number)) {
				stationsInData.remove(i);
				logger.debug("Fire station mapping for a station deleted");
			}
		}
		dataSafetyNet.setFirestations(stationsInData);

	}

}
