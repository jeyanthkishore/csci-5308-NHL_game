package com.dhl.g05.division;

import java.util.List;

import com.dhl.g05.team.ITeam;

public interface IDivision {

	String getDivisionName();

	void setDivisionName(String divisionName);

	List<ITeam> getTeamDetails();

	void setTeamDetails(List<ITeam> teamDetails);

	DivisionConstant validate();

	int saveDivisionObject(int conferenceId, IDivisionModelPersistence divisionDatabase);

	int loadDivisionObject(int conferenceId, IDivisionModelPersistence divisionDatabase);

}