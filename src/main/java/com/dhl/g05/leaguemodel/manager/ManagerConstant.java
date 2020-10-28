package com.dhl.g05.leaguemodel.manager;

public enum ManagerConstant {

	Success("success"),
	Failure("fails"),
	ManagerNameEmpty("Manager Name Should Not have Empty Value");

	private String value; 

	ManagerConstant(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
