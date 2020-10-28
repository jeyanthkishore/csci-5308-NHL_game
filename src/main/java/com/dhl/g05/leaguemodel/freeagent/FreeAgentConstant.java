package com.dhl.g05.leaguemodel.freeagent;

public enum FreeAgentConstant {

	Success("success"),
	Failure("fails"),
	PlayerValueEmpty("Player Should Not have Empty Value"),
	PlayerPositionWrong("Player Position Is Wrong"),
	PlayerAgeInvalid("Player age is invalid"),
	CaptainNull("Captain Cannot be Null"),
	PlayerStateInvalid("Invalid state of player");

	private String value; 

	FreeAgentConstant(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
