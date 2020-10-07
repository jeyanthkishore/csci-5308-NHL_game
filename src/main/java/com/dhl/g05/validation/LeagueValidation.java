package com.dhl.g05.validation;

import java.util.List;

import com.dhl.g05.leaguemodel.ConferenceObject;
import com.dhl.g05.leaguemodel.PlayerObject;

public class LeagueValidation {
	private String leagueName;
	private List<ConferenceObject> conferenceDetails;
	private List<PlayerObject> freeAgent;
	
	public LeagueValidation() {
		setLeagueName(null);
		setConferenceDetails(null);
		setFreeAgent(null);
	}
	
	public LeagueValidation(ILeagueModelValidation leagueObject) {
		leagueObject.loadLeagueData(this);
	}
	
	public LeagueValidation(String league, List<ConferenceObject> conferencedetail,List<PlayerObject> agent) {
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
	
    public String validateLeague() {
    	if(isLeagueNameEmptyOrNull()) {
    		return "League Name is empty";
    	}
    	if(isConferenceListEmpty()) {
    		return "Conference List is empty";
    	}
    	if(!hasEvenNumberConference()) {
    		return "Conference must be evenly sized";
    	}
    	if(isFreeAgentListEmpty()) {
    		return "Free Agent List is empty";
    	}
    	if(isFreeAgentDetailsEmptyOrNull()) {
    		return "Free Agent Attribue is empty";
    	}
    	if(isFreeAgentPositionWrong()) {
    		return "Position of the player cannot be different";
    	}
    	if(isAgentCaptainTrue()) {
    		return "Free Agent Cannot be Captain";
    	}
    	if(isCaptainNotFalseBoolean()) {
    		return "Captain Should be a False Boolean";
    	}
    	return "success";
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
	public boolean isCaptainNotFalseBoolean() {
		Boolean captainBoolean = freeAgent.stream().anyMatch(v -> !(v.getTeamPlayers().get("captain").equals(false)));
		return captainBoolean;
	}
	
	public boolean isAgentCaptainTrue() {
		Boolean captainBoolean = freeAgent.stream().anyMatch(v -> (v.getTeamPlayers().get("captain").equals(true)));
		return captainBoolean;
	}
	
	public int checkLeaguePresent() {
		return 1;
	}

}
