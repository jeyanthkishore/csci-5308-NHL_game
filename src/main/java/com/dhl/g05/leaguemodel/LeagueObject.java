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
	
	public LeagueObject(ILeagueValidation leagueObject) {
		leagueObject.loadLeagueData(this);
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

	public boolean isLeagueNameEmptyOrNull() {
		if(leagueName == "" || leagueName== null) {
			return true;
		}
		return false;
	}
	
	public boolean isConferenceListEmpty() {
		return conferenceDetails.isEmpty();
	}
	
	public boolean hasEvenNumberConference() {
		if(conferenceDetails.size()%2 ==0) {
			return true;
		}
		return false;
	}
	
	public boolean isFreeAgentPositionWrong() {
		Boolean position = freeAgent.stream().anyMatch(v -> !(v.getTeamPlayers().get("position").equals("forward")
				|| v.getTeamPlayers().get("position").equals("defense") || v.getTeamPlayers().get("position").equals("goalie")));
		return position;
	}
	public boolean isFreeAgentListEmpty() {
		return freeAgent.isEmpty();
	}
	public boolean isFreeAgentDetailsEmptyOrNull() {
		Boolean emptyOrNull = freeAgent.stream().anyMatch(v -> v.getTeamPlayers().get("playerName")==""
				||v.getTeamPlayers().get("position")==""||v.getTeamPlayers().get("captain")==""
				||v.getTeamPlayers().get("playerName")==null||v.getTeamPlayers().get("position")==null||v.getTeamPlayers().get("captain")==null);
		return emptyOrNull;
	}
	public boolean isAgentCaptainNotFalseBoolean() {
		Boolean captainBoolean = freeAgent.stream().anyMatch(v -> !(v.getTeamPlayers().get("captain").equals(false)));
		return captainBoolean;
	}
	public boolean isAgentCaptainTrue() {
		return false;
	}
	
	public int checkLeaguePresent() {
		return 1;
	}
	
}
