package com.dhl.g05.leaguemodel;

import java.util.List;

import com.dhl.g05.operation.IDataBasePersistence;

public class DivisionObject {

	private String divisionName;
	private List<TeamObject> teams;
	
	public DivisionObject() {
		setDivisionName(null);
		setTeamDetails(null);
	}
	
	public DivisionObject(ILeagueModel divisionObject) {
		divisionObject.LoadDivisionModelData(this);
	}
	
	public DivisionObject(String division, List<TeamObject> teamdetail) {
		this.divisionName = division;
		this.teams = teamdetail;
	}
	
	public int saveDivisionObject(int conferenceId,IDataBasePersistence database) {
		return database.saveDivisionObject(conferenceId,this);
	}
	public int loadDivisionObject(int conferenceId,IDataBasePersistence database) {
		return database.loadDivisionObject(conferenceId,this);
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public List<TeamObject> getTeamDetails() {
		return teams;
	}

	public void setTeamDetails(List<TeamObject> teamDetails) {
		this.teams = teamDetails;
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
		if(teams == null || teams.isEmpty()) {
			return true;
		}
		return false;
	}
}
