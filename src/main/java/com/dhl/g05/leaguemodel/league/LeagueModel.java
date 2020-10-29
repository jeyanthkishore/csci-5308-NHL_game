package com.dhl.g05.leaguemodel.league;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dhl.g05.leaguemodel.coach.CoachModel;
import com.dhl.g05.leaguemodel.conference.ConferenceModel;
import com.dhl.g05.leaguemodel.freeagent.FreeAgentModel;
import com.dhl.g05.leaguemodel.gameplayconfig.GamePlayConfigModel;
import com.dhl.g05.leaguemodel.manager.ManagerModel;

public class LeagueModel {

	private String leagueName;
	private List<ConferenceModel> conferences;
	private List<FreeAgentModel> freeAgents;
	private List<CoachModel> coaches;
	private List<ManagerModel> managerList;
	private ILeagueModelPersistence object;
	private GamePlayConfigModel gamePlayConfig;

	public LeagueModel() {
		setLeagueName(null);
		setConferenceDetails(null);
		setFreeAgent(null);
		setFreeCoach(null);
		setManagerList(null);
		setGamePlayConfig(null);
	}

	public LeagueModel(String league, List<ConferenceModel> conferencedetail,List<FreeAgentModel> agent, List<CoachModel> coach, List<ManagerModel> managers,GamePlayConfigModel gamePlay ,ILeagueModelPersistence dbObject) {
		setLeagueName(league);
		setConferenceDetails(conferencedetail);
		setFreeAgent(agent);
		setFreeCoach(coach);
		setManagerList(managers);
		setGamePlayConfig(gamePlay);
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

	public List<ManagerModel> getManagerList() {
		return managerList;
	}

	public void setManagerList(List<ManagerModel> managerList) {
		this.managerList = managerList;
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

	public int loadLeagueObject(String leagueName,ILeagueModelPersistence database) {
		return database.loadLeagueObject(leagueName,this);
	}

	public LeagueConstant validate() {
		if(isLeagueNameEmptyOrNull()) {
			return LeagueConstant.LeagueNameEmpty;
		}
		if(isConferenceListEmpty()) {
			return LeagueConstant.ConferenceListEmpty;
		}
		if(hasOddNumberConference()) {
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
		if(isManagerListEmpty()){
			return LeagueConstant.ManagerListEmpty;
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

	public boolean hasOddNumberConference() {
		if(conferences != null) {
			return (conferences.size()%2 ==1);
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

	public boolean isManagerListEmpty() {
		if(managerList.isEmpty()) {
			return true;
		}
		return false;
	}

	public GamePlayConfigModel getGamePlayConfig() {
		return gamePlayConfig;
	}

	public void setGamePlayConfig(GamePlayConfigModel gamePlayConfig) {
		this.gamePlayConfig = gamePlayConfig;
	}

}
