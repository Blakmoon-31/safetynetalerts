package com.openclassrooms.safetynetalerts.model;

public class FireStation {

	private String address;
	private String station;

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the station
	 */
	public String getStation() {
		return station;
	}

	/**
	 * @param station the station to set
	 */
	public void setStation(String station) {
		this.station = station;
	}

	@Override
	public String toString() {
		return "FireStation {\"address\"=\"" + address + "\", \"station\"=\"" + station + "\"}";
	}
}
