package com.dhl.g05.leaguemodel.conference;

public interface IConferenceModelPersistence {
	
	public int saveConferenceObject(int leagueId, ConferenceObject conferenceObject);
	
	public int loadConferenceObject(int leagueId, ConferenceObject conferenceObject);

}
