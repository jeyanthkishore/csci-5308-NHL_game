package com.dhl.g05.leaguemodel;

public enum ValidateEnumModel {
	
	Success("success"),
	Failure("fails"),
	LeagueNameEmpty("League Name Is Empty"),
	ConferenceListEmpty("Conference List Is Empty"),
	NoEvenConferenceCount("Conference Count Must Be Even"),
	FreeAgentsEmpty("Free Agent List Is Empty"),
	FreeAgentAttributeEmpty("Free Agent Attribue Is Empty"),
	ImproperPlayerPosition("Position Of The Player Cannot Be Different"),
	LeagueExists("League Already Present"),
	CoachListEmpty("Coach List is Empty"),
	ConferenceNameEmpty("Conference Name Is Empty"),
	DivisionListEmpty("Division List Is Empty"),
	NoEvenDivisionCount("Division Count Must Be Even"),
	DivisionNameEmpty("DivisionName Cannot Be Empty"),
	TeamListEmpty("Team List Is Empty"),
	PlayerValueEmpty("Player Should Not have Empty Value"),
	PlayerPositionWrong("Player Position Is Wrong"),
	PlayerAgeInvalid("Player age is invalid"),
	PlayerStateInvalid("Invalid state of player"),
	CaptainNull("Captain Cannot be Null"),
	TeamDetailsEmpty("Team Details Are Empty"),
	PlayerListEmpty("Player List Is Empty"),
	MaxPlayerCountExceed("Maximum Player Limit Is 20"),
	NoTeamCaptain("Team Must Contain Atleast One Captain"),
	MoreTeamCaptain("Team Must Contain Only One Captain"),
	CoachDetailsEmpty("Coach has missing values");

	private String value; 
	
	ValidateEnumModel(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
