package com.dhl.g05.model;

import java.util.List;

public interface IDivision {

	String getDivisionName();

	void setDivisionName(String divisionName);

	List<ITeam> getTeamDetails();

	void setTeamDetails(List<ITeam> teamDetails);

	DivisionConstant validate();

}