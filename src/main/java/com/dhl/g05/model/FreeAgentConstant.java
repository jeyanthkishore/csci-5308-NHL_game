package com.dhl.g05.model;

public enum FreeAgentConstant {

	Success("success"),
	PlayerValueEmpty("Player Should Not have Empty Value"),
	PlayerPositionWrong("Player Position Is Wrong"),
	CaptainNull("Captain Cannot be Null"),
	PlayerStateInvalid("Invalid state of player"), 
	PlayerBirthdateInvalid("Player birth date is not valid");

	private String value; 

	FreeAgentConstant(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
