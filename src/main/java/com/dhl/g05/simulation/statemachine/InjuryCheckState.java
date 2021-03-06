package com.dhl.g05.simulation.statemachine;

import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.communication.IPlayerCommunication;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.IPlayer;
import com.dhl.g05.model.IPlayerInjured;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.model.ModelAbstractFactory;
import com.dhl.g05.simulation.DateHandler;
import com.dhl.g05.simulation.SimulationAbstractFactory;

public class InjuryCheckState extends AbstractState{
	
	static final Logger logger = LogManager.getLogger(InjuryCheckState.class);
	private ILeague league;
	private IPlayerCommunication communication;
	private ITeam firstTeam;
	private ITeam secondTeam;
	private LocalDate currentDate;

	public InjuryCheckState(IPlayerCommunication communication, ITeam teamOne, ITeam teamTwo) {
		this.communication = communication;
		this.firstTeam = teamOne;
		this.secondTeam = teamTwo;
	}

	@Override
	public boolean enter() {
		logger.info("Entering into InjuryCheckState");
		
		league = this.getLeague();
		currentDate = league.getLeagueCurrentDate();
		return true;
	}

	@Override
	public boolean performStateTask() {
		ModelAbstractFactory modelFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();
		IPlayerInjured playerInjury = modelFactory.createPlayerInjury();
		IInjury injuryConfig = league.getGamePlayConfig().getInjuriesConfig();
		for (IPlayer player: firstTeam.getPlayerList()) {
			if (player.checkPlayerInjury(playerInjury, player, injuryConfig)) {
				logger.info(player.getPlayerName() +StateMachineConstant.InjurDaysText.getValue()
				+player.getInjuredForNumberOfDays());
				communication.sendMessage(player.getPlayerName() +StateMachineConstant.InjurDaysText.getValue()
						+player.getInjuredForNumberOfDays());
			}
		}

		for (IPlayer player: secondTeam.getPlayerList()) {
			if (player.checkPlayerInjury(playerInjury, player, injuryConfig)) {
				logger.info(player.getPlayerName() +StateMachineConstant.InjurDaysText.getValue()
				+player.getInjuredForNumberOfDays());
				communication.sendMessage(player.getPlayerName() + StateMachineConstant.InjurDaysText.getValue() 
						+player.getInjuredForNumberOfDays());
			}
		}
		return true;
	}

	@Override
	public boolean exit() {
		logger.info("Exiting InjuryCheckState");
		
		SimulationAbstractFactory simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
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
