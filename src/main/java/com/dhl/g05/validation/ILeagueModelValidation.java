package com.dhl.g05.validation;

public interface ILeagueModelValidation {

	void loadPlayerData(PlayerValidation playerObject);

	void loadTeamData(TeamValidation teamObject);
	
    public void LoadDivisionData(DivisionValidation divisionObject);
	
	public void loadLeagueData(LeagueValidation leaguObject);
	
	public void loadConferenceData(ConferenceValidation conferenceObject);
}
