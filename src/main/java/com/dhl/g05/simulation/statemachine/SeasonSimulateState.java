package com.dhl.g05.simulation.statemachine;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.simulation.SimulationAbstractFactory;

public class SeasonSimulateState extends AbstractState {
	private int seasons;

	public SeasonSimulateState(int choice) {
		this.seasons = choice;
	}

	@Override
	public boolean enter() {
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
			AbstractState initializeSeasonState = simulationFactory.createInitializeSeasonState();
			simulationFactory.createStateMachine(initializeSeasonState).enterState();
		}
		return false;
	}
}
