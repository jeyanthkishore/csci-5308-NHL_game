package com.dhl.g05.league;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dhl.g05.coach.CoachModel;
import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.freeagent.FreeAgentModel;
import com.dhl.g05.gameplayconfig.GamePlayConfigModel;
import com.mysql.cj.util.StringUtils;

public class LeagueModel {

	private String leagueName;
	private List<ConferenceModel> conferences;
	private List<FreeAgentModel> freeAgents;
	private List<CoachModel> coaches;
	private List<String> generalManagers;
	private ILeagueModelPersistence dbObject;
	private GamePlayConfigModel gameplayConfig;

	public LeagueModel() {
		setLeagueName(null);
		setConferenceDetails(null);
		setFreeAgent(null);
		setFreeCoach(null);
		setManagerList(null);
		setGamePlayConfig(null);
	}

	public LeagueModel(String league, List<ConferenceModel> conferencedetail,List<FreeAgentModel> agent, List<CoachModel> coach, List<String> managers,GamePlayConfigModel gamePlay ,ILeagueModelPersistence dbObject) {
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

	public List<String> getManagerList() {
		return generalManagers;
	}

	public void setManagerList(List<String> managerList) {
		this.generalManagers = managerList;
	}

	public void setDbObject(ILeagueModelPersistence object) {
		this.dbObject = object;
	}
	
	public GamePlayConfigModel getGamePlayConfig() {
		return gameplayConfig;
	}

	public void setGamePlayConfig(GamePlayConfigModel gamePlayConfig) {
		this.gameplayConfig = gamePlayConfig;
	}
	public int saveLeagueObject(ILeagueModelPersistence database) {
		return database.saveLeagueObject(this);
	}

	public int loadLeagueObject(int leagueId,ILeagueModelPersistence database) {
		return database.loadLeagueObject(leagueId,this);
	}

	public int loadLeagueFromTeam(String teamName, ILeagueModelPersistence database) {
		return database.loadLeagueFromTeam(teamName);
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
		if( generalManagers == null ||generalManagers.isEmpty()) {
			return true;
		}
		return false;
	}

}
