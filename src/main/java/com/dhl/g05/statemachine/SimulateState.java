package com.dhl.g05.statemachine;

import com.dhl.g05.ApplicationConfiguration;

public class SimulateState extends AbstractState {
	private int seasons;

	public SimulateState(int choice) {
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
		StateMachineAbstractFactory stateFactory = ApplicationConfiguration.instance().getStateMachineConcreteFactoryState();
		for (int i = 0; i < seasons; i++) {
			AbstractState initializeSeasonState = stateFactory.createInitializeSeasonState();
			stateFactory.createStateMachine(initializeSeasonState).enterState();
		}
		return false;
	}
}
