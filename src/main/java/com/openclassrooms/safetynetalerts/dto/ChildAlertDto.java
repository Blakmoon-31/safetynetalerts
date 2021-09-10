package com.openclassrooms.safetynetalerts.dto;

import java.util.List;

public class ChildAlertDto {

	private List<ChildrenDto> children;
	private List<OtherMembersDto> otherMembers;

	/**
	 * @return the child
	 */
	public List<ChildrenDto> getChildren() {
		return children;
	}

	/**
	 * @param child the child to set
	 */
	public void setChildren(List<ChildrenDto> children) {
		this.children = children;
	}

	/**
	 * @return the otherMember
	 */
	public List<OtherMembersDto> getOtherMembers() {
		return otherMembers;
	}

	/**
	 * @param otherMember the otherMember to set
	 */
	public void setOtherMembers(List<OtherMembersDto> otherMembers) {
		this.otherMembers = otherMembers;
	}

}
