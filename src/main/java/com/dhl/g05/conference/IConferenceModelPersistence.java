package com.dhl.g05.conference;

public interface IConferenceModelPersistence {
	
	public int saveConferenceObject(int leagueId, IConference conferenceObject);
	
	public int loadConferenceObject(int leagueId, IConference conferenceObject);

}
