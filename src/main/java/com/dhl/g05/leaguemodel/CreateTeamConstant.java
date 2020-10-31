package com.dhl.g05.leaguemodel;

public enum CreateTeamConstant {
	
	
	ErrorCoachCreation("Error Creating Coach for the team"),
	ErrorPlayerCreation("Error Creating players for the team"),
	ErrorManagerCreation("Error Creating Manager for the team"),
	SelectCoach("Select a Coach from the below list --"),
	AddCoach("Enter a Number to add coach"),
	InvalidNumber("Invalid Number......."), 
	EnterKeyMessage("Press Enter to Continue"), 
	SelectManager("Select a Manager from the below lits --"), 
	TeamCount("Total Team Strength = "),
	SkaterCount("Number of Skaters = "),
	GoalieCount("Number of Goalies = "), 
	SelectFreeAgent("Select a Free Agent from the below list --"), 
	AddPlayer("Enter a Number to add player"), 
	CaptainConfirmation("Do Want him to be captain (Yes/No) : "),
	Yes("yes"),
	Goalie("goalie"),
	Separator(" | "),
	MaximumGoalieMessage("Maximum Two Goalie per Team"),
	MaximumSkatersMessage("Maximum Skater Count Reached,Pls Select Goalie");
	
	private String value; 

	CreateTeamConstant(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}


}
