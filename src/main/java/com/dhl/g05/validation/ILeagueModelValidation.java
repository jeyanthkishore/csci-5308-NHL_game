package com.dhl.g05.validation;

public interface ILeagueModelValidation {

	public void loadPlayerData(PlayerValidation playerObject);

	public void loadTeamData(TeamValidation teamObject);
	
    public void LoadDivisionData(DivisionValidation divisionObject);
	
	public void loadLeagueData(LeagueValidation leaguObject);
	
	public void loadConferenceData(ConferenceValidation conferenceObject);
}
