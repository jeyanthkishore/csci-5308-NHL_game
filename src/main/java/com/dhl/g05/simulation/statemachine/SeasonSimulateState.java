package com.dhl.g05.simulation.statemachine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.simulation.SimulationAbstractFactory;

public class SeasonSimulateState extends AbstractState {
	
	static final Logger logger = LogManager.getLogger(SeasonSimulateState.class);
	private int seasons;

	public SeasonSimulateState(int choice) {
		this.seasons = choice;
	}

	@Override
	public boolean enter() {
		logger.info("Entering into SeasonSimulateState");
		
		return true;
	}

	@Override
	public boolean performStateTask() {
		return true;
	}

	@Override
	public boolean exit() {
		SimulationAbstractFactory simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		for (int i = 0; i < seasons; i++) {
			logger.info("Simulating Season "+seasons);
			AbstractState initializeSeasonState = simulationFactory.createInitializeSeasonState();
			simulationFactory.createStateMachine(initializeSeasonState).enterState();
		}
		logger.info("Exiting SeasonSimulateState");
		return false;
	}
}
