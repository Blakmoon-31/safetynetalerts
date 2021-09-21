package com.openclassrooms.safetynetalerts.dto;

import java.util.List;

public class ChildAlertDto {

	private List<ChildAlertChildrenDto> children;
	private List<ChildAlertOtherMembersDto> otherMembers;

	/**
	 * @return the children list
	 */
	public List<ChildAlertChildrenDto> getChildren() {
		return children;
	}

	/**
	 * @param children - the list children to set
	 */
	public void setChildren(List<ChildAlertChildrenDto> children) {
		this.children = children;
	}

	/**
	 * @return the otherMembers
	 */
	public List<ChildAlertOtherMembersDto> getOtherMembers() {
		return otherMembers;
	}

	/**
	 * @param otherMembers - the list otherMembers to set
	 */
	public void setOtherMembers(List<ChildAlertOtherMembersDto> otherMembers) {
		this.otherMembers = otherMembers;
	}

}
