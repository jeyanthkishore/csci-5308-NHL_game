package com.dhl.g05.leaguemodel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dhl.g05.operation.IDataBasePersistence;
import com.dhl.g05.operation.OperationModel;

public class LeagueObject {

	private String leagueName;
	private List<ConferenceObject> conferenceDetails;
	private List<PlayerObject> freeAgent;
	private IDataBasePersistence object;
	private String result;
	
	public LeagueObject() {
		setLeagueName(null);
		setConferenceDetails(null);
		setFreeAgent(null);
	}
	
	public LeagueObject(ILeagueModel leagueObject) {
		leagueObject.loadLeagueModelData(this);
	}
	
	public LeagueObject(String league, List<ConferenceObject> conferencedetail,List<PlayerObject> agent,IDataBasePersistence dbObject) {
		setLeagueName(league);
		setConferenceDetails(conferencedetail);
		setFreeAgent(agent);
		this.object = dbObject;
		result = validate();
	}

	public String getLeagueName() {
		return leagueName;
	}

	public void setLeagueName(String league) {
		this.leagueName = league;
	}

	public List<ConferenceObject> getConferenceDetails() {
		return conferenceDetails;
	}

	public void setConferenceDetails(List<ConferenceObject> conferencedetail) {
		this.conferenceDetails = conferencedetail;
	}

	public List<PlayerObject> getFreeAgent() {
		return freeAgent;
	}

	public void setFreeAgent(List<PlayerObject> agent) {
		this.freeAgent = agent;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	public String validate() {
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
		if(conferenceDetails != null) {
			return (conferenceDetails.size()<=0);
		}
		 return true;
	}
	
	public boolean hasEvenNumberConference() {
		if(conferenceDetails != null) {
			return (conferenceDetails.size()%2 ==0);
		}
		return false;
	}
	
	public boolean isFreeAgentPositionWrong() {
		Boolean position = freeAgent.stream().anyMatch(v ->!(v.getPosition().equals("forward")
				|| v.getPosition().equals("defense") ||v.getPosition().equals("goalie")));
		return position;
	}
	public boolean isFreeAgentListEmpty() {
		if(freeAgent != null) {
			return (freeAgent.size()<=0);
		}
		return true;
	}
	public boolean isFreeAgentDetailsEmptyOrNull() {
		Boolean emptyOrNull = freeAgent.stream().anyMatch(v -> v.getPlayerName() ==""
				||v.getPosition() ==""||v.getPlayerName()==null||v.getPosition()==null
				||v.getCaptain()==null);
		return emptyOrNull;
	}
	
	public boolean isAgentCaptainTrue() {
		Boolean captainBoolean = freeAgent.stream().anyMatch(v -> v.getCaptain().equals(true));
		return captainBoolean;
	}
	
	public Boolean checkLeaguePresent() {
		OperationModel check = new OperationModel(object);
		ArrayList<HashMap<String,Object>> allLeague = check.getLeagueCheck();
		Boolean leaguePresent = allLeague.stream().anyMatch(v->v.get("league_name").equals(leagueName));
		return leaguePresent;
	}
}
