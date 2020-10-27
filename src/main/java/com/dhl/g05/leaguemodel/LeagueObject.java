package com.dhl.g05.leaguemodel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dhl.g05.operation.IDataBasePersistence;

public class LeagueObject {

	private String leagueName;
	private List<ConferenceObject> conferences;
	private List<FreeAgentObject> freeAgents;
	private List<CoachObject> coaches;
	private IDataBasePersistence object;
	
	public LeagueObject() {
		setLeagueName(null);
		setConferenceDetails(null);
		setFreeAgent(null);
		setFreeCoach(null);
	}

	public LeagueObject(String league, List<ConferenceObject> conferencedetail,List<FreeAgentObject> agent, List<CoachObject> coach,IDataBasePersistence dbObject) {
		setLeagueName(league);
		setConferenceDetails(conferencedetail);
		setFreeAgent(agent);
		setFreeCoach(coach);
		this.object = dbObject;
	}

	public LeagueObject(ILeagueModel leagueObject) {
		leagueObject.loadLeagueModelData(this);
	}

	public String getLeagueName() {
		return leagueName;
	}

	public void setLeagueName(String league) {
		this.leagueName = league;
	}

	public List<ConferenceObject> getConferenceDetails() {
		return conferences;
	}

	public void setConferenceDetails(List<ConferenceObject> conferencedetail) {
		this.conferences = conferencedetail;
	}

	public List<FreeAgentObject> getFreeAgent() {
		return freeAgents;
	}

	public void setFreeAgent(List<FreeAgentObject> agent) {
		this.freeAgents = agent;
	}

	public List<CoachObject> getFreeCoach() {
		return coaches;
	}

	public void setFreeCoach(List<CoachObject> freeCoach) {
		this.coaches = freeCoach;
	}

	public IDataBasePersistence getObject() {
		return object;
	}

	public void setObject(IDataBasePersistence object) {
		this.object = object;
	}

	public int saveLeagueObject(IDataBasePersistence database) {
		return database.saveLeagueObject(this);
	}

	public int saveLeagueCoachObject(int leagueId, IDataBasePersistence database) {
		return database.saveLeagueCoachObject(leagueId, this);
	}

	public int loadLeagueObject(String leagueName,IDataBasePersistence database) {
		return database.loadLeagueObject(leagueName,this);
	}

	public int loadLeagueCoachObject(String leagueName, IDataBasePersistence database) {
		return database.loadLeagueCoachObject(leagueName, this);
	}

	public ValidateEnumModel validate() {
    	if(isLeagueNameEmptyOrNull()) {
    		return ValidateEnumModel.LeagueNameEmpty;
    	}
    	if(isConferenceListEmpty()) {
    		return ValidateEnumModel.ConferenceListEmpty;
    	}
    	if(!hasEvenNumberConference()) {
    		return ValidateEnumModel.NoEvenConferenceCount;
    	}
    	if(isFreeAgentListEmpty()) {
    		return ValidateEnumModel.FreeAgentsEmpty;
    	}
    	if(isFreeAgentDetailsEmptyOrNull()) {
    		return ValidateEnumModel.FreeAgentAttributeEmpty;
    	}
    	if(isFreeAgentPositionWrong()) {
    		return ValidateEnumModel.ImproperPlayerPosition;
    	}
    	if(checkLeaguePresent()) {
    		return ValidateEnumModel.LeagueExists;
    	}
    	if(isCoachListEmpty()){
    		return ValidateEnumModel.CoachListEmpty;
    	}
    	return ValidateEnumModel.Success;
	}

	public boolean isLeagueNameEmptyOrNull() {
		if(leagueName==""|| leagueName == null) {
			return true;
		}
		return false;
	}
	
	public boolean isConferenceListEmpty() {
		if(conferences != null) {
			return (conferences.size()<=0);
		}
		 return true;
	}
	
	public boolean hasEvenNumberConference() {
		if(conferences != null) {
			return (conferences.size()%2 ==0);
		}
		return false;
	}
	
	public boolean isFreeAgentPositionWrong() {
		Boolean position = freeAgents.stream().anyMatch(v ->!(v.getPosition().equalsIgnoreCase("forward")
				|| v.getPosition().equalsIgnoreCase("defense") ||v.getPosition().equalsIgnoreCase("goalie")));
		return position;
	}

	public boolean isFreeAgentListEmpty() {
		if(freeAgents != null) {
			return (freeAgents.size()<=0);
		}
		return true;
	}

	public boolean isFreeAgentDetailsEmptyOrNull() {
		Boolean emptyOrNull = freeAgents.stream().anyMatch(v -> v.getPlayerName() ==""
				||v.getPosition() ==""||v.getPlayerName()==null||v.getPosition()==null);
		return emptyOrNull;
	}

	public Boolean checkLeaguePresent() {
		ArrayList<HashMap<String,Object>> allLeague = object.loadDetails();
		Boolean leaguePresent = allLeague.stream().anyMatch(v->v.get("league_name").equals(leagueName));
		return leaguePresent;
	}

	public boolean isCoachListEmpty() {
		if(coaches.isEmpty()) {
			return true;
		}
		return false;
	}

}
