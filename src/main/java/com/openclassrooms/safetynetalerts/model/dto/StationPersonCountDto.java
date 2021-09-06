package com.openclassrooms.safetynetalerts.model.dto;

public class StationPersonCountDto {

	private int adults;
	private int children;

	/**
	 * @return the adults
	 */
	public int getAdults() {
		return adults;
	}

	/**
	 * @param adults the adults to set
	 */
	public void setAdults(int adults) {
		this.adults = adults;
	}

	/**
	 * @return the childs
	 */
	public int getChildren() {
		return children;
	}

	/**
	 * @param childs the childs to set
	 */
	public void setChildren(int children) {
		this.children = children;
	}

}
