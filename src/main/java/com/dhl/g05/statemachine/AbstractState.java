package com.dhl.g05.statemachine;

public abstract class AbstractState {
	private StateMachine outerStateMachine;
	private StateMachine innerStateMachine;
	
	public abstract void enter();
	public abstract void performStateTask();
	public abstract void exit();
	
	public AbstractState(StateMachine stateMachine) {
		this.setOuterStateMachine(stateMachine);
	}
	
	public void transitionState(AbstractState state) {
		outerStateMachine.setCurrentState(state);
	}
	
	public StateMachine getInnerStateMachine() {
		return innerStateMachine;
	}
	
	public void setInnerStateMachine(StateMachine stateMachine) {
		this.innerStateMachine = stateMachine;		
	}
	
	public void runInnerStateMachine() {
		innerStateMachine.enterState();
	}
	
	public StateMachine getOuterStateMachine() {
		return outerStateMachine;
	}
	
	public void setOuterStateMachine(StateMachine stateMachine) {
		this.outerStateMachine = stateMachine;
		this.outerStateMachine.setCurrentState(this);
	}
	
}
