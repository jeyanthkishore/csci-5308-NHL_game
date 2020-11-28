package com.dhl.g05.simulation;

public class StateMachine implements IStateMachine {

	private AbstractState currentState;   

	public StateMachine(AbstractState state) {
		setCurrentState(state);
	}

	public AbstractState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(AbstractState state) {
		currentState = state;
	}

	public void enterState() {
		if (currentState.enter()) {
			runState();
		} else {
			enterState();
		}
	}

	public boolean runState() {
		if (currentState.performStateTask()) {
			return exitState();
		} else {
			enterState();
		}
		return false;
	}

	public boolean exitState() {

		if (currentState.exit()) {
			currentState = currentState.getNextState();
			System.out.println(currentState);
			if (currentState==null) {
				return false;
			}else {
				enterState();
			}
		}
		return false;
	}

}
