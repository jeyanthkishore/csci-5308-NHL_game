package com.dhl.g05.leaguemodel.division;

import java.util.List;

import com.dhl.g05.leaguemodel.ILeagueModelComplete;
import com.dhl.g05.leaguemodel.ValidateEnumModel;
import com.dhl.g05.leaguemodel.team.TeamObject;

public class DivisionObject {

	private String divisionName;
	private List<TeamObject> teams;
	
	public DivisionObject() {
		setDivisionName(null);
		setTeamDetails(null);
	}
	
	public DivisionObject(ILeagueModelComplete divisionObject) {
		divisionObject.LoadDivisionModelData(this);
	}
	
	public DivisionObject(String division, List<TeamObject> teamdetail) {
		this.divisionName = division;
		this.teams = teamdetail;
	}
	
	public int saveDivisionObject(int conferenceId,IDivisionModelPersistence database) {
		return database.saveDivisionObject(conferenceId,this);
	}
	public int loadDivisionObject(int conferenceId,IDivisionModelPersistence database) {
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
	public ValidateEnumModel validate() {
		if(isDivisionNameEmptyorNull()) {
			return ValidateEnumModel.DivisionNameEmpty;
		}
		if(isTeamListEmpty()) {
			return ValidateEnumModel.TeamListEmpty;
		}
		return ValidateEnumModel.Success;
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
