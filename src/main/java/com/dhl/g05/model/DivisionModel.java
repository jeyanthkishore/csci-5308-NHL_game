package com.dhl.g05.model;

import java.util.List;

import com.mysql.cj.util.StringUtils;

public class DivisionModel implements IDivision {

	private String divisionName;
	private List<ITeam> teams;

	public DivisionModel() {
		setDivisionName(null);
		setTeamDetails(null);
	}

	public DivisionModel(IDivisionModel divisionObject) {
		divisionObject.LoadDivisionModelData(this);
	}

	@Override
	public String getDivisionName() {
		return divisionName;
	}

	@Override
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	@Override
	public List<ITeam> getTeamDetails() {
		return teams;
	}

	@Override
	public void setTeamDetails(List<ITeam> teamDetails) {
		this.teams = teamDetails;
	}

	@Override
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
