package com.dhl.g05.conference;

public interface IConferenceModelPersistence {
	
	public int saveConferenceObject(int leagueId, ConferenceModel conferenceObject);
	
	public int loadConferenceObject(int leagueId, ConferenceModel conferenceObject);

}
