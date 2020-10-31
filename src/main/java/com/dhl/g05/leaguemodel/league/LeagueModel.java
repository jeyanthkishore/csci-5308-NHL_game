package com.dhl.g05.leaguemodel.league;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dhl.g05.leaguemodel.coach.CoachModel;
import com.dhl.g05.leaguemodel.conference.ConferenceModel;
import com.dhl.g05.leaguemodel.freeagent.FreeAgentModel;
import com.dhl.g05.leaguemodel.gameplayconfig.GamePlayConfigModel;
import com.dhl.g05.leaguemodel.manager.ManagerModel;
import com.mysql.cj.util.StringUtils;

public class LeagueModel {

	private String leagueName;
	private List<ConferenceModel> conferences;
	private List<FreeAgentModel> freeAgents;
	private List<CoachModel> coaches;
	private List<ManagerModel> managerList;
	private ILeagueModelPersistence dbObject;
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
		setDbObject(dbObject);
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

	public ILeagueModelPersistence getDbObject() {
		return dbObject;
	}

	public void setDbObject(ILeagueModelPersistence object) {
		this.dbObject = object;
	}
	
	public GamePlayConfigModel getGamePlayConfig() {
		return gamePlayConfig;
	}

	public void setGamePlayConfig(GamePlayConfigModel gamePlayConfig) {
		this.gamePlayConfig = gamePlayConfig;
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
		if(isFreeAgentListNotValid()) {
			return LeagueConstant.FreeAgentsNotValid;
		}
		if(isCoachListEmpty()){
			return LeagueConstant.CoachListEmpty;
		}
		if(isManagerListEmpty()){
			return LeagueConstant.ManagerListEmpty;
		}
		if(checkLeaguePresent()) {
			return LeagueConstant.LeagueExists;
		}
		return LeagueConstant.Success;
	}

	private boolean isLeagueNameEmptyOrNull() {
		return StringUtils.isNullOrEmpty(leagueName);
	}

	private boolean isConferenceListEmpty() {
		if(conferences == null) {
			return true;
		}
		return (conferences.size()<=0);
	}

	private boolean hasOddNumberConference() {
		if(conferences == null) {
			return false;
		}
		return (conferences.size()%2 ==1);
	}

	private boolean isFreeAgentListNotValid() {
		if(freeAgents == null) {
			return true;
		}
		else return (freeAgents.size()<=20);
	}

	private Boolean checkLeaguePresent() {
		ArrayList<HashMap<String,Object>> allLeague = dbObject.loadDetails();
		Boolean leaguePresent = allLeague.stream().anyMatch(v->v.get("league_name").equals(leagueName));
		return leaguePresent;
	}

	private boolean isCoachListEmpty() {
		if(coaches.isEmpty()) {
			return true;
		}
		return false;
	}

	private boolean isManagerListEmpty() {
		if(managerList.isEmpty()) {
			return true;
		}
		return false;
	}
}
