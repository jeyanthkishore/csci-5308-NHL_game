package com.dhl.g05.leaguemodel;
import java.util.List;

public class LeagueObject {

	private String leagueName;
	private List<ConferenceObject> conferenceDetails;
	private List<PlayerObject> freeAgent;
	
	public LeagueObject() {
		setLeagueName(null);
		setConferenceDetails(null);
		setFreeAgent(null);
	}
	
	public LeagueObject(ILeagueModel leagueObject) {
		leagueObject.loadLeagueModelData(this);
	}
	
	public LeagueObject(String league, List<ConferenceObject> conferencedetail,List<PlayerObject> agent) {
		setLeagueName(league);
		setConferenceDetails(conferencedetail);
		setFreeAgent(agent);
	}
	
	public String getLeagueName() {
		return leagueName;
	}

	public void setLeagueName(String league) {
		leagueName = league;
	}

	public List<ConferenceObject> getConferenceDetails() {
		return conferenceDetails;
	}

	public void setConferenceDetails(List<ConferenceObject> conferencedetail) {
		conferenceDetails = conferencedetail;
	}

	public List<PlayerObject> getFreeAgent() {
		return freeAgent;
	}

	public void setFreeAgent(List<PlayerObject> agent) {
		freeAgent = agent;
	}

}
