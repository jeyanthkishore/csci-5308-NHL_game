package com.dhl.g05.simulation;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.IConference;
import com.dhl.g05.model.IDivision;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.model.ModelAbstractFactory;

public class StimulateGameState extends AbstractState{
	private ILeague league;
	private ITeam firstTeam;
	private ITeam secondTeam;

	@Override
	public boolean enter() {
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

		schedule.setWinningTeam(teamWon);
		league.getLeagueSchedule().updateScheduleAfterGame(schedule);
		league.getLeagueStanding().updateStatisticsForWinningTeam(winningTeamConference, winningTeamDivision, teamWon);
		league.getLeagueStanding().updateStatisticsForLosingTeam(losingTeamConference, losingTeamDivision, teamLost);

		return true;
	}

	@Override
	public boolean exit() {
		SimulationAbstractFactory simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		this.setNextState(simulationFactory.createInjuryCheckState(firstTeam,secondTeam));
		return true;
	}

}
