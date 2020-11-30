package com.dhl.g05.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.database.IDeserializeModel;
import com.dhl.g05.database.ISerializeModel;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.dhl.g05.simulation.leaguesimulation.ILeagueSchedule;
import com.dhl.g05.simulation.leaguesimulation.ILeagueStanding;
import com.dhl.g05.simulation.statemachine.IGamePlayConfig;
import com.google.gson.annotations.Expose;
import com.mysql.cj.util.StringUtils;

public class LeagueModel implements ILeague {
	
	static final Logger logger = LogManager.getLogger(LeagueModel.class);
	@Expose
	private String leagueName;
	@Expose
	private List<IConference> conferences;
	@Expose
	private List<IFreeAgent> freeAgents;
	@Expose
	private List<IPlayer> retiredPlayersList;
	@Expose
	private List<ICoach> coaches;
	@Expose
	private List<String> generalManagers;
	@Expose
	private IGamePlayConfig gameplayConfig;
	@Expose
	private List<IFreeAgent> retiredFreeAgentsList;
	@Expose
	private int daysSinceStatIncrease;
	@Expose
	private LocalDate leagueCurrentDate;
	@Expose
	private ILeagueStanding leagueStanding;
	@Expose
	private ILeagueSchedule leagueSchedule;
	private SimulationAbstractFactory simulationAbstractFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();

	public LeagueModel() {
		setLeagueName(null);
		setConferenceDetails(null);
		setFreeAgent(null);
		setFreeCoach(null);
		setManagerList(null);
		setGamePlayConfig(null);
		retiredPlayersList = new ArrayList<>();
		retiredFreeAgentsList = new ArrayList<>();
		this.leagueStanding = simulationAbstractFactory.createLeagueStanding();
		this.leagueSchedule = simulationAbstractFactory.createLeagueSchedule();
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
		logger.info("Removing retired freeAgents from league");
		int numberOfFreeAgents = freeAgents.size();
		if (numberOfFreeAgents > 0) {
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
	public IGamePlayConfig getGamePlayConfig() {
		return gameplayConfig;
	}

	@Override
	public void setGamePlayConfig(IGamePlayConfig gamePlayConfig) {
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
		this.daysSinceStatIncrease += 1;
	}

	public ILeagueSchedule getLeagueSchedule() {
		return leagueSchedule;
	}

	public void setLeagueSchedule(ILeagueSchedule leagueSchedule) {
		this.leagueSchedule = leagueSchedule;
	}

	@Override
	public boolean saveLeagueObject(ISerializeModel saveLeague, String teamName) {
		return saveLeague.serialiseObjects(this, teamName);
	}

	@Override
	public ILeague loadLeagueObject(IDeserializeModel loadLeague, String teamName) {
		return loadLeague.deserializeObjects(teamName, this);
	}

	@Override
	public LeagueConstant validate() {
		logger.info("Validating league details");
		if (isLeagueNameEmptyOrNull()) {
			return LeagueConstant.LeagueNameEmpty;
		}
		if (isConferenceListEmpty()) {
			return LeagueConstant.ConferenceListEmpty;
		}
		if (hasOddNumberConference()) {
			return LeagueConstant.NoEvenConferenceCount;
		}
		if (isFreeAgentListNotValid()) {
			return LeagueConstant.FreeAgentsNotValid;
		}
		if (isCoachListEmpty()) {
			return LeagueConstant.CoachListEmpty;
		}
		if (isManagerListEmpty()) {
			return LeagueConstant.ManagerListEmpty;
		}
		return LeagueConstant.Success;
	}

	public void addNewFreeAgentsToLeague(List<IFreeAgent> freeAgentList) {
		List<IFreeAgent> AllfreeAgents = getFreeAgent();
		if (AllfreeAgents == null) {
			setFreeAgent(freeAgentList);
		} else {
			AllfreeAgents.addAll(freeAgentList);
		}
		setFreeAgent(AllfreeAgents);
	}

	private boolean isLeagueNameEmptyOrNull() {
		return StringUtils.isNullOrEmpty(leagueName);
	}

	private boolean isConferenceListEmpty() {
		if (conferences == null) {
			return true;
		}
		return (conferences.size() <= 0);
	}

	private boolean hasOddNumberConference() {
		return (conferences.size() % 2 == 1);
	}

	private boolean isFreeAgentListNotValid() {
		if (freeAgents == null) {
			return true;
		} else
			return (freeAgents.size() <= 20);
	}

	private boolean isCoachListEmpty() {
		if (coaches.isEmpty()) {
			return true;
		}
		return false;
	}

	private boolean isManagerListEmpty() {
		if (generalManagers == null || generalManagers.isEmpty()) {
			return true;
		}
		return false;
	}

}
