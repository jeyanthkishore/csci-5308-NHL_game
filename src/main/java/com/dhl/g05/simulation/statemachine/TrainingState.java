package com.dhl.g05.simulation.statemachine;

import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.IConference;
import com.dhl.g05.model.IDivision;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.IPlayer;
import com.dhl.g05.model.IPlayerTraining;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.model.ModelAbstractFactory;
import com.dhl.g05.simulation.DateHandler;
import com.dhl.g05.simulation.SimulationAbstractFactory;

public class TrainingState extends AbstractState{
	
	static final Logger logger = LogManager.getLogger(TrainingState.class);
	private ILeague league;

	@Override
	public boolean enter() {
		logger.info("Entering into TrainingState");
		
		league = this.getLeague();
		league.incrementDaysSinceStatIncrease();
		return true;
	}

	@Override
	public boolean performStateTask() {
		ModelAbstractFactory modelFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();
		
		int daysUntilStatIncreaseCheck = league.getGamePlayConfig().getTrainingConfig().getDaysUntilStatIncreaseCheck();
        int daysSinceLastStatIncrease = league.getDaysSinceStatIncrease();
        if (daysSinceLastStatIncrease > daysUntilStatIncreaseCheck) {
        	IPlayerTraining training = modelFactory.createPlayerTraining();

        	for (IConference conference : league.getConferenceDetails()) {
        		for (IDivision division : conference.getDivisionDetails()) {
        			for (ITeam team : division.getTeamDetails()) {
        				for(IPlayer player : team.getPlayerList()) {
        					training.performTrainingForPlayer(player,team.getCoachDetails(),league.getGamePlayConfig().getInjuriesConfig());
        				}
        			}
        		}
        	}
        }
		return true;
	}

	@Override
	public boolean exit() {
		logger.info("Exiting TrainingState");
		
		SimulationAbstractFactory simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		LocalDate currentDate = league.getLeagueCurrentDate();
		System.out.println(currentDate);
		if (league.getLeagueSchedule().isGamesUnplayedOnCurrentDay(currentDate)) {
            this.setNextState(simulationFactory.createStimulateGameState());
        }
        else if (DateHandler.instance().isTradeDeadlinePassed(currentDate)) {
        	this.setNextState(simulationFactory.createAgingState());
        }
        else {
        	this.setNextState(simulationFactory.createTradeState());
        }
		return true;
	}

}
