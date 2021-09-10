package com.openclassrooms.safetynetalerts.dto;

import java.util.List;

public class PersonInfoDto {

	private String firstName;
	private String lastName;
	private String email;
	private String address;

	private List<String> medications;
	private List<String> allergies;

	private int age;

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
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
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the phone
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the addressString
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param addressString the addressString to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the medications
	 */
	public List<String> getMedications() {
		return medications;
	}

	/**
	 * @param medications the medications to set
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
	 * @param allergies the allergies to set
	 */
	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

}
