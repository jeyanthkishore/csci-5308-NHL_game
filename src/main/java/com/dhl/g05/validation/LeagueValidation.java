package com.dhl.g05.validation;

import java.util.List;

import com.dhl.g05.leaguemodel.ConferenceObject;
import com.dhl.g05.leaguemodel.PlayerObject;
import com.dhl.g05.operation.IDataBasePersistence;
import com.dhl.g05.operation.OperationModel;

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
    		return "League Name Is Empty";
    	}
    	if(isConferenceListEmpty()) {
    		return "Conference List Is Empty";
    	}
    	if(!hasEvenNumberConference()) {
    		return "Conference Count Must Be Even";
    	}
    	if(isFreeAgentListEmpty()) {
    		return "Free Agent List Is Empty";
    	}
    	if(isFreeAgentDetailsEmptyOrNull()) {
    		return "Free Agent Attribue Is Empty";
    	}
    	if(isFreeAgentPositionWrong()) {
    		return "Position Of The Player Cannot Be Different";
    	}
    	if(isAgentCaptainTrue()) {
    		return "Free Agent Cannot Be Captain";
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
		Boolean position = freeAgent.stream().anyMatch(v ->!(v.getPostition().equals("forward")
				|| v.getPostition().equals("defense") ||v.getPostition().equals("goalie")));
		return position;
	}
	public boolean isFreeAgentListEmpty() {
		return freeAgent.isEmpty();
	}
	public boolean isFreeAgentDetailsEmptyOrNull() {
		Boolean emptyOrNull = freeAgent.stream().anyMatch(v -> v.getPlayerName() ==""
				||v.getPostition() ==""||v.getPlayerName()==null||v.getPostition()==null
				||v.getCaptain()==null);
		return emptyOrNull;
	}
	
	public boolean isAgentCaptainTrue() {
		Boolean captainBoolean = freeAgent.stream().anyMatch(v -> v.getCaptain().equals(true));
		return captainBoolean;
	}
	
	public Boolean checkLeaguePresent(IDataBasePersistence object) {
		OperationModel check = new OperationModel(leagueName, object);
		return check.isLeagueCheck();
	}

}
