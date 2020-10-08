package com.dhl.g05.leaguemodel;

import java.util.List;

public class DivisionObject {

	private String divisionName;
	private List<TeamObject> teamDetails;
	
	public DivisionObject() {
		setDivisionName(null);
		setTeamDetails(null);
	}
	
	public DivisionObject(ILeagueValidation divisionObject) {
		divisionObject.LoadDivisionModelData(this);
	}
	
	public DivisionObject(String division, List<TeamObject> teamdetail) {
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
}
