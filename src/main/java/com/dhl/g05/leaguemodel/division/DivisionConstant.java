package com.dhl.g05.leaguemodel.division;

public enum DivisionConstant {

	Success("success"),
	Failure("fails"),
	DivisionNameEmpty("DivisionName Cannot Be Empty"),
	TeamListEmpty("Team List Is Empty");

	private String value; 

	DivisionConstant(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
