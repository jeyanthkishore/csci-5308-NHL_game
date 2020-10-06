package com.dhl.g05.leaguemodel;

public interface ILeagueValidation {

	public void loadTeamData(TeamObject teamObject);
	
	public void loadPlayerData(PlayerObject playerObject);
	
	public void LoadDivisionData(DivisionObject divisionObject);
	
	public void loadLeagueData(LeagueObject leaguObject);
	
	public void loadConferenceData(ConferenceObject conferenceObject);
	
}
