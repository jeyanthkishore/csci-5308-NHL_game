package com.dhl.g05.statemachine;

public abstract class AbstractState {
	private StateMachine outerStateMachine;
	private StateMachine innerStateMachine;
	private boolean completedState;
	private AbstractState nextState;
	private String playerInput;
	
	public abstract boolean enter();
	public abstract boolean performStateTask();
	public abstract boolean exit();
	
	public AbstractState(StateMachine stateMachine) {
		this.setOuterStateMachine(stateMachine);
	}
	
	public StateMachine getInnerStateMachine() {
		return innerStateMachine;
	}
	
	public void setInnerStateMachine(StateMachine stateMachine) {
		this.innerStateMachine = stateMachine;		
	}
	
	public boolean runInnerStateMachine() {
		return innerStateMachine.enterState();
	}
	
	public StateMachine getOuterStateMachine() {
		return outerStateMachine;
	}
	
	public void setOuterStateMachine(StateMachine stateMachine) {
		this.outerStateMachine = stateMachine;
	}
	
	public void setNextState(AbstractState state) {
		this.nextState = state;
	}
	
	public AbstractState getNextState() {
		return nextState;
	}
	
	public void setPlayerInput(String input) {
		this.playerInput = input;
	}
	
	public String getPlayerInput() {
		return	playerInput;
	}
	
}
