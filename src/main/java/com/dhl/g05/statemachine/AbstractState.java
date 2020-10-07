package com.dhl.g05.statemachine;

public abstract class AbstractState {
	private StateMachine outerStateMachine;
	private StateMachine innerStateMachine;
	
	public abstract void enter();
	public abstract void performStateTask();
	public abstract void exit();
	
	public void transitionState(AbstractState state) {
		//TODO:
	}
	
	public StateMachine getInnerStateMachine() {
		return null;
	}
	
	public void setInnerStateMachine(StateMachine stateMachine) {
		//TODO:
		
	}
	
	public void runInnerStateMachine() {
		//TODO:
		
	}
	
	public StateMachine getOuterStateMachine() {
		return null;
	}
	
}
