package com.dhl.g05.statemachine;

import java.time.LocalDate;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.IConference;
import com.dhl.g05.model.IDivision;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.IPlayer;
import com.dhl.g05.model.IPlayerTraining;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.model.PlayerTraining;
import com.dhl.g05.model.RandomNumberFactory;

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
		StateMachineAbstractFactory stateFactory = ApplicationConfiguration.instance().getStateMachineConcreteFactoryState();
		LocalDate currentDate = league.getLeagueCurrentDate();
		System.out.println(currentDate);
		if (league.getLeagueSchedule().isGamesUnplayedOnCurrentDay(currentDate)) {
            this.setNextState(stateFactory.createStimulateGameState());
        }
        else if (DateHandler.getInstance().isTradeDeadlinePassed(currentDate)) {
        	this.setNextState(stateFactory.createAgingState());
        }
        else {
        	this.setNextState(stateFactory.createTradeState());
        }
		return true;
	}

}
