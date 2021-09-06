package com.openclassrooms.safetynetalerts.model.dto;

import java.util.List;

public class ChildAlertDto {

	private List<ChildDto> child;
	private List<OtherMemberDto> otherMember;

	/**
	 * @return the child
	 */
	public List<ChildDto> getChild() {
		return child;
	}

	/**
	 * @param child the child to set
	 */
	public void setChild(List<ChildDto> child) {
		this.child = child;
	}

	/**
	 * @return the otherMember
	 */
	public List<OtherMemberDto> getOtherMember() {
		return otherMember;
	}

	/**
	 * @param otherMember the otherMember to set
	 */
	public void setOtherMember(List<OtherMemberDto> otherMember) {
		this.otherMember = otherMember;
	}

}
