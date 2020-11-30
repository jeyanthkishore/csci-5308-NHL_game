package com.dhl.g05.simulation.statemachine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.IConference;
import com.dhl.g05.model.IDivision;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.model.ModelAbstractFactory;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.dhl.g05.simulation.leaguesimulation.IScheduleModel;

public class SimulateGameState extends AbstractState{
	
	static final Logger logger = LogManager.getLogger(SimulateGameState.class);
	private ILeague league;
	private ITeam firstTeam;
	private ITeam secondTeam;

	@Override
	public boolean enter() {
		logger.info("Entering into SimulateGameState");
		
		league = this.getLeague();
		return true;
	}

	@Override
	public boolean performStateTask() {
		IConference winningTeamConference;
		IDivision winningTeamDivision;
		ITeam teamWon;
		IConference losingTeamConference;
		IDivision losingTeamDivision;
		ITeam teamLost;
		ModelAbstractFactory modelFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();
		double randomChance = modelFactory.createRandomNumber().generateRandomDoubleNumber(0, 1);
		IScheduleModel schedule = league.getLeagueSchedule().getMatchOnCurrentDate(league.getLeagueCurrentDate());
		firstTeam = schedule.getFirstTeam();
		secondTeam = schedule.getSecondTeam();

		double teamAStrength = firstTeam.getTeamStrength();
		double teamBStrength = secondTeam.getTeamStrength();

		if (teamAStrength > teamBStrength) {
			teamWon = firstTeam;
			teamLost = secondTeam;
		}
		else {
			teamWon = secondTeam;
			teamLost = firstTeam;
		}

		if (randomChance < league.getGamePlayConfig().getGameResolverConfig().getRandomWinChance()) {
			if (teamWon == firstTeam) {
				teamWon = secondTeam;
				teamLost = firstTeam;
			}
			else {
				teamWon = firstTeam;
				teamLost = secondTeam;
			}
		}

		if (teamWon == firstTeam) {
			winningTeamConference = schedule.getFirstConference();
			winningTeamDivision = schedule.getFirstDivision();
			losingTeamConference = schedule.getSecondConference();
			losingTeamDivision = schedule.getSecondDivision();
		}
		else {
			winningTeamConference = schedule.getSecondConference();
			winningTeamDivision = schedule.getSecondDivision();
			losingTeamConference = schedule.getFirstConference();
			losingTeamDivision = schedule.getFirstDivision();
		}

		logger.info("Match Won by "+teamWon.getTeamName());
		logger.info("Match Lost by "+teamLost.getTeamName());
		schedule.setWinningTeam(teamWon);
		league.getLeagueSchedule().updateScheduleAfterGame(schedule);
		league.getLeagueStanding().updateStatisticsForWinningTeam(winningTeamConference, winningTeamDivision, teamWon);
		league.getLeagueStanding().updateStatisticsForLosingTeam(losingTeamConference, losingTeamDivision, teamLost);

		return true;
	}

	@Override
	public boolean exit() {
		logger.info("Exiting SimulateGameState");
		
		SimulationAbstractFactory simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		this.setNextState(simulationFactory.createInjuryCheckState(firstTeam,secondTeam));
		return true;
	}

}
