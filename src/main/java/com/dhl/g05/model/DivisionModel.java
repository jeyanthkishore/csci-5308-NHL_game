package com.dhl.g05.model;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.annotations.Expose;
import com.mysql.cj.util.StringUtils;

public class DivisionModel implements IDivision {
	static final Logger logger = LogManager.getLogger(ConferenceModel.class);
	@Expose
	private String divisionName;
	@Expose
	private List<ITeam> teams;

	public DivisionModel() {
		setDivisionName(null);
		setTeamDetails(null);
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
		logger.info("Validating Division Details");
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
