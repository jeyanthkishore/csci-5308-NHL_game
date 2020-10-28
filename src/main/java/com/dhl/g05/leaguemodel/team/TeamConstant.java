package com.dhl.g05.leaguemodel.team;

public enum TeamConstant {

	Success("success"),
	Failure("fails"),
	TeamDetailsEmpty("Team Details Are Empty"),
	PlayerListEmpty("Player List Is Empty"),
	MaxPlayerCountExceed("Maximum Player Limit Is 20"),
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
