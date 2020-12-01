package com.dhl.g05.simulation.statemachine;

import java.util.InputMismatchException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.communication.IPlayerCommunication;
import com.dhl.g05.simulation.SimulationAbstractFactory;

public class PlayerChoiceState extends AbstractState{

	static final Logger logger = LogManager.getLogger(PlayerChoiceState.class);
	private IPlayerCommunication communication;
	private int choice;

	public PlayerChoiceState(IPlayerCommunication communicate) {
		this.communication = communicate;
	}

	@Override
	public boolean enter() {
		logger.info("Entering into PlayerChoiceState");
		
		communication.sendMessage(StateMachineConstant.SeasonSimulate.getValue());
		return true;
	}

	@Override
	public boolean performStateTask() {
		try {
			this.choice = communication.getResponseNumber();
		}catch(InputMismatchException e) {
			logger.warn("String entered by user instead of number");
			communication.sendMessage(StateMachineConstant.NoNumberEntered.getValue());
			return false;
		}
		return true;
	}

	@Override
	public boolean exit() {
		logger.info("Exiting PlayerChoiceState");
		
		SimulationAbstractFactory simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		this.setNextState(simulationFactory.createStimulateState(choice));
		return true;
	}
}
