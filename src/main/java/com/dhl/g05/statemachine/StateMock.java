package com.dhl.g05.statemachine;

public class StateMock implements IState {
	
	private StateMachine outerStateMachine;
	private StateMachine innerStateMachine;
	
	private boolean enterState = false;
	private boolean ranState = false;
	private boolean exitState = false;

	

	public StateMock(StateMachine stateMachine) {
		
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

	@Override
	public void transitionState(IState state) {
		outerStateMachine.setCurrentState(state);
		
	}

	@Override
	public StateMachine getInnerStateMachine() {
		return innerStateMachine;
	}

	@Override
	public void setInnerStateMachine(StateMachine stateMachine) {
		innerStateMachine = stateMachine;
	}

	@Override
	public void runInnerStateMachine() {
		innerStateMachine.enterState();
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
