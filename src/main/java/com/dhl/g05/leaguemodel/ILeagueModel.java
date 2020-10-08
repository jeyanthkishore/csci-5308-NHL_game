package com.dhl.g05.leaguemodel;

public interface ILeagueModel {

	public void loadTeamModelData(TeamObject teamObject);
	
	public void loadPlayerModelData(PlayerObject playerObject);
	
	public void LoadDivisionModelData(DivisionObject divisionObject);
	
	public void loadLeagueModelData(LeagueObject leaguObject);
	
	public void loadConferenceModelData(ConferenceObject conferenceObject);
	
}
