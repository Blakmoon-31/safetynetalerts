package com.openclassrooms.safetynetalerts.dto;

import java.util.List;

public class FireDto {

	private String firstName;
	private String lastName;
	private String phone;

	private List<String> medications;
	private List<String> allergies;

	private String station;

	private int age;

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName - the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName - the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone - the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the medications
	 */
	public List<String> getMedications() {
		return medications;
	}

	/**
	 * @param medications - the list medications to set
	 */
	public void setMedications(List<String> medications) {
		this.medications = medications;
	}

	/**
	 * @return the allergies
	 */
	public List<String> getAllergies() {
		return allergies;
	}

	/**
	 * @param allergies - the list allergies to set
	 */
	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}

	/**
	 * @return the station
	 */
	public String getStation() {
		return station;
	}

	/**
	 * @param station - the station to set
	 */
	public void setStation(String station) {
		this.station = station;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age - the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

}
