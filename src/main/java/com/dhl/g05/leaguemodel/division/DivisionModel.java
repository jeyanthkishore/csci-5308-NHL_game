package com.dhl.g05.leaguemodel.division;

import java.util.List;

import com.dhl.g05.leaguemodel.team.TeamModel;

public class DivisionModel {

	private String divisionName;
	private List<TeamModel> teams;

	public DivisionModel() {
		setDivisionName(null);
		setTeamDetails(null);
	}

	public DivisionModel(IDivisionModel divisionObject) {
		divisionObject.LoadDivisionModelData(this);
	}

	public DivisionModel(String division, List<TeamModel> teamdetail) {
		this.divisionName = division;
		this.teams = teamdetail;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public List<TeamModel> getTeamDetails() {
		return teams;
	}

	public void setTeamDetails(List<TeamModel> teamDetails) {
		this.teams = teamDetails;
	}

	public int saveDivisionObject(int conferenceId,IDivisionModelPersistence database) {
		return database.saveDivisionObject(conferenceId,this);
	}
	
	public int loadDivisionObject(int conferenceId,IDivisionModelPersistence database) {
		return database.loadDivisionObject(conferenceId,this);
	}

	public DivisionConstant validate() {
		if(isDivisionNameEmptyorNull()) {
			return DivisionConstant.DivisionNameEmpty;
		}
		if(isTeamListEmpty()) {
			return DivisionConstant.TeamListEmpty;
		}
		return DivisionConstant.Success;
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
