package com.dhl.g05.league;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dhl.g05.coach.ICoach;
import com.dhl.g05.conference.IConference;
import com.dhl.g05.freeagent.IFreeAgent;
import com.dhl.g05.gameplayconfig.GamePlayConfigModel;
import com.dhl.g05.leaguesimulation.leagueschedule.ILeagueSchedule;
import com.dhl.g05.leaguesimulation.leagueschedule.LeagueSchedule;
import com.dhl.g05.leaguesimulation.leaguestanding.ILeagueStanding;
import com.dhl.g05.leaguesimulation.leaguestanding.LeagueStanding;
import com.dhl.g05.player.IPlayer;
import com.mysql.cj.util.StringUtils;

public class LeagueModel implements ILeague{

	private String leagueName;
	private List<IConference> conferences;
	private List<IFreeAgent> freeAgents;
	private List<IPlayer> retiredPlayersList;
	private List<ICoach> coaches;
	private List<String> generalManagers;
	private ILeagueModelPersistence dbObject;
	private GamePlayConfigModel gameplayConfig;
	private List<IFreeAgent> retiredFreeAgentsList;
	private int daysSinceStatIncrease;
	private LocalDate leagueCurrentDate;
	private ILeagueStanding leagueStanding;
	private ILeagueSchedule leagueSchedule;

	public LeagueModel() {
		setLeagueName(null);
		setConferenceDetails(null);
		setFreeAgent(null);
		setFreeCoach(null);
		setManagerList(null);
		setGamePlayConfig(null);
		retiredPlayersList = new ArrayList<>();
		this.leagueStanding = new LeagueStanding();
		this.leagueSchedule = new LeagueSchedule();
		retiredFreeAgentsList = new ArrayList<>();
	}

	public LeagueModel(String league, List<IConference> conferencedetail,List<IFreeAgent> agent, List<ICoach> coach, List<String> managers,GamePlayConfigModel gamePlay) {
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

	@Override
	public String getLeagueName() {
		return leagueName;
	}

	@Override
	public void setLeagueName(String league) {
		this.leagueName = league;
	}

	@Override
	public List<IConference> getConferenceDetails() {
		return conferences;
	}

	@Override
	public void setConferenceDetails(List<IConference> conferencedetail) {
		this.conferences = conferencedetail;
	}

	@Override
	public List<IFreeAgent> getFreeAgent() {
		return freeAgents;
	}

	@Override
	public void setFreeAgent(List<IFreeAgent> agent) {
		this.freeAgents = agent;
	}

	@Override
	public List<IPlayer> getRetiredPlayersList() {
		return retiredPlayersList;
	}

	@Override
	public void setRetiredPlayersList(List<IPlayer> retiredPlayersList) {
		this.retiredPlayersList = retiredPlayersList;
	}

	@Override
	public List<ICoach> getFreeCoach() {
		return coaches;
	}

	@Override
	public void setFreeCoach(List<ICoach> freeCoach) {
		this.coaches = freeCoach;
	}

	@Override
	public boolean removeRetiredFreeAgentsFromLeague(IFreeAgent freeAgent) {
		int numberOfFreeAgents = freeAgents.size();
		if(numberOfFreeAgents > 0) {
			freeAgents.remove(freeAgent);
			return true;
		}
		return false;
	}

	@Override
	public List<IFreeAgent> getRetiredFreeAgentsList() {
		return retiredFreeAgentsList;
	}

	@Override
	public void setRetiredFreeAgentsList(List<IFreeAgent> retiredFreeAgentsList) {
		this.retiredFreeAgentsList = retiredFreeAgentsList;
	}

	@Override
	public void addRetiredFreeAgentToList(IFreeAgent freeAgent) {
		retiredFreeAgentsList.add(freeAgent);
	}

	@Override
	public void addRetiredPlayersToList(IPlayer player) {
		retiredPlayersList.add(player);
	}

	@Override
	public List<String> getManagerList() {
		return generalManagers;
	}

	@Override
	public void setManagerList(List<String> managerList) {
		this.generalManagers = managerList;
	}

	@Override
	public void setDbObject(ILeagueModelPersistence object) {
		this.dbObject = object;
	}

	@Override
	public GamePlayConfigModel getGamePlayConfig() {
		return gameplayConfig;
	}

	@Override
	public void setGamePlayConfig(GamePlayConfigModel gamePlayConfig) {
		this.gameplayConfig = gamePlayConfig;
	}
	
	public ILeagueStanding getLeagueStanding() {
		return leagueStanding;
	}

	public void setLeagueStanding(ILeagueStanding leagueStanding) {
		this.leagueStanding = leagueStanding;
	}

	public LocalDate getLeagueCurrentDate() {
		return leagueCurrentDate;
	}
	
	public void incrementCurrentDate() {
		leagueCurrentDate = leagueCurrentDate.plusDays(1);
	}
	

	public void setLeagueCurrentDate(LocalDate leagueCurrentDate) {
		this.leagueCurrentDate = leagueCurrentDate;
	}

	public int getDaysSinceStatIncrease() {
		return daysSinceStatIncrease;
	}

	public void setDaysSinceStatIncrease(int daysSinceStatIncrease) {
		this.daysSinceStatIncrease = daysSinceStatIncrease;
	}
	
    public void resetDaysSinceStatIncrease() {
        this.daysSinceStatIncrease = 0;
    }
    
    public void incrementDaysSinceStatIncrease() {
    	this.daysSinceStatIncrease +=1;
    }
	
	public ILeagueSchedule getLeagueSchedule() {
		return leagueSchedule;
	}

	public void setLeagueSchedule(ILeagueSchedule leagueSchedule) {
		this.leagueSchedule = leagueSchedule;
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

	@Override
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
//		if(checkLeaguePresent()) {
//			return LeagueConstant.LeagueExists;
//		}
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
