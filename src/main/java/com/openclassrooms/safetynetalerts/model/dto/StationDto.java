package com.openclassrooms.safetynetalerts.model.dto;

import java.util.List;

public class StationDto {

	private List<StationPersonDto> persons;
	private StationPersonCountDto count;

	/**
	 * @return the persons
	 */
	public List<StationPersonDto> getPersons() {
		return persons;
	}

	/**
	 * @param persons the persons to set
	 */
	public void setPersons(List<StationPersonDto> persons) {
		this.persons = persons;
	}

	/**
	 * @return the count
	 */
	public StationPersonCountDto getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(StationPersonCountDto count) {
		this.count = count;
	}

}
