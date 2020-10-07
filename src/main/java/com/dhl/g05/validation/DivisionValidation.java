package com.dhl.g05.validation;

import java.util.List;

import com.dhl.g05.leaguemodel.TeamObject;

public class DivisionValidation {
	private String divisionName;
	private List<TeamObject> teamDetails;
	
	public DivisionValidation() {
		setDivisionName(null);
		setTeamDetails(null);
	}
	
	public DivisionValidation(ILeagueModelValidation divisionObject) {
		divisionObject.LoadDivisionData(this);
	}
	
	public DivisionValidation(String division, List<TeamObject> teamdetail) {
		this.divisionName = division;
		this.teamDetails = teamdetail;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public List<TeamObject> getTeamDetails() {
		return teamDetails;
	}
	public void setTeamDetails(List<TeamObject> teamDetails) {
		this.teamDetails = teamDetails;
	}
	
	public String validateDivision() {
		if(isDivisionNameEmptyorNull()) {
			return "DivisionName Cannot be empty";
		}
		if(isTeamListEmpty()) {
			return "TeamList is empty";
		}
		return "success";
	}
	
	public boolean isDivisionNameEmptyorNull() {
		if(divisionName == "" || divisionName == null) {
			return true;
		}
		return false;
	}
	
	public boolean isTeamListEmpty() {
		return teamDetails.isEmpty();
	}

}
