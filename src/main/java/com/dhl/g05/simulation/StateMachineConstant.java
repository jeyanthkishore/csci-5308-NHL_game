package com.dhl.g05.simulation;

public enum StateMachineConstant {
	
	ImportStart("Enter a file name to create a new team or hit enter to load an existing team:"),
	LeagueCreationIssue("Issue creating league, try again"), 
	EnterTeam("Enter team name:"), 
	NoTeam("Team does not exist"), 
	SeasonSimulate("Enter the number of Season to stimulate"),
	NoNumberEntered("No Number Was Entered, Please Enter a Number..");
	
	private String value; 

	StateMachineConstant(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}