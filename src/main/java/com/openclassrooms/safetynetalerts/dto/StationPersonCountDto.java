package com.openclassrooms.safetynetalerts.dto;

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
	 * @param adults - the adults to set
	 */
	public void setAdults(int adults) {
		this.adults = adults;
	}

	/**
	 * @return the children
	 */
	public int getChildren() {
		return children;
	}

	/**
	 * @param children - the children to set
	 */
	public void setChildren(int children) {
		this.children = children;
	}

}
