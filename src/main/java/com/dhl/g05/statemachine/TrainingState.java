package com.dhl.g05.statemachine;

import java.time.LocalDate;

import com.dhl.g05.Training.IPlayerTraining;
import com.dhl.g05.Training.PlayerTraining;
import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.leaguesimulation.DateHandler;
import com.dhl.g05.player.IPlayer;
import com.dhl.g05.player.RandomNumberFactory;
import com.dhl.g05.team.ITeam;

public class TrainingState extends AbstractState{
	private ILeague league;

	@Override
	public boolean enter() {
		league = this.getLeague();
		league.incrementDaysSinceStatIncrease();
		return true;
	}

	@Override
	public boolean performStateTask() {
		int daysUntilStatIncreaseCheck = league.getGamePlayConfig().getTraining().getDaysUntilStatIncreaseCheck();
        int daysSinceLastStatIncrease = league.getDaysSinceStatIncrease();
        if (daysSinceLastStatIncrease > daysUntilStatIncreaseCheck) {
        	IPlayerTraining training = new PlayerTraining(new RandomNumberFactory());

        	for (IConference conference : league.getConferenceDetails()) {
        		for (IDivision division : conference.getDivisionDetails()) {
        			for (ITeam team : division.getTeamDetails()) {
        				for(IPlayer player : team.getPlayerList()) {
        					training.performTrainingForPlayer(player,team.getCoachDetails(),league);
        				}
        			}
        		}
        	}
        }
		return true;
	}

	@Override
	public boolean exit() {
		LocalDate currentDate = league.getLeagueCurrentDate();
		if (league.getLeagueSchedule().anyUnplayedGamesOnDate(currentDate)) {
            this.setNextState(AbstractStateMachineFactory.getFactory().getStimulateGameState());
        }
        else if (DateHandler.getInstance().isTradeDeadlinePassed(currentDate)) {
        	this.setNextState(AbstractStateMachineFactory.getFactory().getAgingState());
        }
        else {
        	this.setNextState(AbstractStateMachineFactory.getFactory().getTradeState());
        }
		return true;
	}

}
