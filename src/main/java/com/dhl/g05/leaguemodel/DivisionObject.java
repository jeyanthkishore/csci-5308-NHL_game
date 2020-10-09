package com.dhl.g05.leaguemodel;

import java.util.List;

public class DivisionObject {

	private String divisionName;
	private List<TeamObject> teamDetails;
	private String result;
	
	public DivisionObject() {
		setDivisionName(null);
		setTeamDetails(null);
	}
	
	public DivisionObject(ILeagueModel divisionObject) {
		divisionObject.LoadDivisionModelData(this);
	}
	
	public DivisionObject(String division, List<TeamObject> teamdetail) {
		this.divisionName = division;
		this.teamDetails = teamdetail;
		result = validate();
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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
	public String validate() {
		if(isDivisionNameEmptyorNull()) {
			return "DivisionName Cannot Be Empty";
		}
		if(isTeamListEmpty()) {
			return "TeamList Is Empty";
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
