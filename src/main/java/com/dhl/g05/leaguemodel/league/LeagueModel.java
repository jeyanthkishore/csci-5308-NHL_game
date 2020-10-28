package com.dhl.g05.leaguemodel.league;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dhl.g05.leaguemodel.coach.CoachModel;
import com.dhl.g05.leaguemodel.coach.ICoachModelPersistence;
import com.dhl.g05.leaguemodel.conference.ConferenceModel;
import com.dhl.g05.leaguemodel.freeagent.FreeAgentModel;

public class LeagueModel {

	private String leagueName;
	private List<ConferenceModel> conferences;
	private List<FreeAgentModel> freeAgents;
	private List<CoachModel> coaches;
	private ILeagueModelPersistence object;

	public LeagueModel() {
		setLeagueName(null);
		setConferenceDetails(null);
		setFreeAgent(null);
		setFreeCoach(null);
	}

	public LeagueModel(String league, List<ConferenceModel> conferencedetail,List<FreeAgentModel> agent, List<CoachModel> coach,ILeagueModelPersistence dbObject) {
		setLeagueName(league);
		setConferenceDetails(conferencedetail);
		setFreeAgent(agent);
		setFreeCoach(coach);
		this.object = dbObject;
	}

	public LeagueModel(ILeagueModel leagueObject) {
		leagueObject.loadLeagueModelData(this);
	}

	public String getLeagueName() {
		return leagueName;
	}

	public void setLeagueName(String league) {
		this.leagueName = league;
	}

	public List<ConferenceModel> getConferenceDetails() {
		return conferences;
	}

	public void setConferenceDetails(List<ConferenceModel> conferencedetail) {
		this.conferences = conferencedetail;
	}

	public List<FreeAgentModel> getFreeAgent() {
		return freeAgents;
	}

	public void setFreeAgent(List<FreeAgentModel> agent) {
		this.freeAgents = agent;
	}

	public List<CoachModel> getFreeCoach() {
		return coaches;
	}

	public void setFreeCoach(List<CoachModel> freeCoach) {
		this.coaches = freeCoach;
	}

	public ILeagueModelPersistence getObject() {
		return object;
	}

	public void setObject(ILeagueModelPersistence object) {
		this.object = object;
	}

	public int saveLeagueObject(ILeagueModelPersistence database) {
		return database.saveLeagueObject(this);
	}

	public int saveLeagueCoachObject(int leagueId, ICoachModelPersistence database) {
		return database.saveLeagueCoachObject(leagueId, this);
	}

	public int loadLeagueObject(String leagueName,ILeagueModelPersistence database) {
		return database.loadLeagueObject(leagueName,this);
	}

	public int loadLeagueCoachObject(String leagueName, ICoachModelPersistence database) {
		return database.loadLeagueCoachObject(leagueName, this);
	}

	public LeagueConstant validate() {
		if(isLeagueNameEmptyOrNull()) {
			return LeagueConstant.LeagueNameEmpty;
		}
		if(isConferenceListEmpty()) {
			return LeagueConstant.ConferenceListEmpty;
		}
		if(!hasEvenNumberConference()) {
			return LeagueConstant.NoEvenConferenceCount;
		}
		if(isFreeAgentListEmpty()) {
			return LeagueConstant.FreeAgentsEmpty;
		}
		if(isFreeAgentDetailsEmptyOrNull()) {
			return LeagueConstant.FreeAgentAttributeEmpty;
		}
		if(isFreeAgentPositionWrong()) {
			return LeagueConstant.ImproperPlayerPosition;
		}
		if(checkLeaguePresent()) {
			return LeagueConstant.LeagueExists;
		}
		if(isCoachListEmpty()){
			return LeagueConstant.CoachListEmpty;
		}
		return LeagueConstant.Success;
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
