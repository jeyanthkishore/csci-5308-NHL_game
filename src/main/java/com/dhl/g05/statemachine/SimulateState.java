package com.dhl.g05.statemachine;

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
		for (int i = 0; i < seasons; i++) {
			AbstractState initializeSeasonState = AbstractStateMachineFactory.getFactory().getInitializeSeasonState();
			AbstractStateMachineFactory.getFactory().getStateMachine(initializeSeasonState).enterState();
		}
		return false;
	}
}
