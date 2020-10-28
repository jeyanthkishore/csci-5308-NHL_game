package com.dhl.g05.leaguemodel.league;

public enum LeagueConstant {

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
	ManagerListEmpty("Manager List is Empty");

	private String value; 

	LeagueConstant(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
