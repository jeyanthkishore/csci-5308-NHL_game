package com.dhl.g05.statemachine;

public class StateMock extends AbstractState {

	private boolean enterState = false;
	private boolean ranState = false;
	private boolean exitState = false;

	public StateMock(StateMachine stateMachine) {
		super(stateMachine);
	}
	
	@Override
	public void enter() {
		enterState = true;
	}

	@Override
	public void performStateTask() {
		ranState = true;
	}

	@Override
	public void exit() {
		exitState = true;
	}

	public boolean didEnterState() {
		return enterState;
	}
	
	public boolean didRunState() {
		return ranState;
	}
	
	public boolean didExitState() {
		return exitState;
	}


}
