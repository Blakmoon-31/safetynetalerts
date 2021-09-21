package com.openclassrooms.safetynetalerts.dto;

import java.util.List;

public class FloodDto {

	private String address;
	private List<FloodInhabitantsDto> inhabitants;

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address - the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the inhabitants
	 */
	public List<FloodInhabitantsDto> getInhabitants() {
		return inhabitants;
	}

	/**
	 * @param inhabitants - the list inhabitants to set
	 */
	public void setInhabitants(List<FloodInhabitantsDto> inhabitants) {
		this.inhabitants = inhabitants;
	}

}
