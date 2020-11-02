package com.dhl.g05.team;

public enum TeamConstant {

	Success("success"),
	Failure("fails"),
	TeamDetailsEmpty("Team Details Are Empty"),
	PlayerListEmpty("Player List Is Empty"),
	PlayerCountMismatch("Team must contain 20 Players"),
	NoTeamCaptain("Team Must Contain Atleast One Captain"),
	MoreTeamCaptain("Team Must Contain Only One Captain"),
	CoachDetailsEmpty("Coach has missing values");

	private String value; 

	TeamConstant(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
