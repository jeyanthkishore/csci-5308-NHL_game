package com.dhl.g05.division;

import java.util.List;

import com.dhl.g05.team.TeamModel;
import com.mysql.cj.util.StringUtils;

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

	private boolean isDivisionNameEmptyorNull() {
		return StringUtils.isNullOrEmpty(divisionName);
	}

	private boolean isTeamListEmpty() {
		if(teams == null || teams.isEmpty()) {
			return true;
		}
		return false;
	}
}