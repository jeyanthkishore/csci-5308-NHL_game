package com.dhl.g05.operation;

public interface ISavePersistance {

	public void loadLeagueModel(SaveTeamInformation saveTeamObject);

	public void loadLeagueModel(SavePlayerInformation savePlayerInformation);

	public void loadLeagueModel(SaveLeagueObject saveLeagueObject);

	public void loadLeagueModel(SaveConferenceObject saveConferenceObject);
	
}
