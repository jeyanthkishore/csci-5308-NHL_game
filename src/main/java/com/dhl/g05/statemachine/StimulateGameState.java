package com.dhl.g05.statemachine;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.leaguesimulation.IScheduleModel;
import com.dhl.g05.player.IRandomNumberFactory;
import com.dhl.g05.player.RandomNumberFactory;
import com.dhl.g05.team.ITeam;

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
        ITeam winningTeam;
        IConference losingTeamConference;
        IDivision losingTeamDivision;
        ITeam losingTeam;

        IScheduleModel schedule = league.getLeagueSchedule().getMatchOnCurrentDate(league.getLeagueCurrentDate());
        ITeam teamA = schedule.getFirstTeam();
        ITeam teamB = schedule.getSecondTeam();


        double teamAStrength = teamA.getTeamStrength();
        double teamBStrength = teamB.getTeamStrength();

        if (teamAStrength > teamBStrength) {
            winningTeam = teamA;
            losingTeam = teamB;
        }
        else {
            winningTeam = teamB;
            losingTeam = teamA;
        }

        boolean flipResult = false;
        IRandomNumberFactory randomChance = new RandomNumberFactory();
        double randomUpsetValue = randomChance.generateRandomDoubleNumber(0, 1);

//        if (randomUpsetValue < league.getGamePlayConfig().getGameResolver().getRandomWinChance()) {
            flipResult = true;
//        }

        if (flipResult) {
            if (winningTeam == teamA) {
                winningTeam = teamB;
                losingTeam = teamA;
            }
            else {
                winningTeam = teamA;
                losingTeam = teamB;
            }
        }

        if (winningTeam == teamA) {
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

        schedule.setWinningTeam(winningTeam);
        league.getLeagueSchedule().updateScheduleAfterGame(schedule);
        league.getLeagueStanding().updateStatisticsForWinningTeam(winningTeamConference, winningTeamDivision, winningTeam);
        league.getLeagueStanding().updateStatisticsForLosingTeam(losingTeamConference, losingTeamDivision, losingTeam);


        return true;
    
	}

	@Override
	public boolean exit() {
		this.setNextState(ApplicationConfiguration.instance().getStateMachineConcreteFactoryState().createAgingState());
		return true;
	}

}
