package com.dhl.g05.simulation;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.IConference;
import com.dhl.g05.model.IDivision;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.model.ModelAbstractFactory;

public class StimulateGameState extends AbstractState{
	private ILeague league;

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
		ITeam teamA = schedule.getFirstTeam();
		ITeam teamB = schedule.getSecondTeam();

		double teamAStrength = teamA.getTeamStrength();
		double teamBStrength = teamB.getTeamStrength();

		if (teamAStrength > teamBStrength) {
			teamWon = teamA;
			teamLost = teamB;
		}
		else {
			teamWon = teamB;
			teamLost = teamA;
		}

		if (randomChance < league.getGamePlayConfig().getGameResolverConfig().getRandomWinChance()) {
			if (teamWon == teamA) {
				teamWon = teamB;
				teamLost = teamA;
			}
			else {
				teamWon = teamA;
				teamLost = teamB;
			}
		}

		if (teamWon == teamA) {
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
		this.setNextState(ApplicationConfiguration.instance().getSimulationConcreteFactoryState().createInjuryCheckState());
		return true;
	}

}
