package com.dhl.g05.statemachine;

public interface IState {
	
	public void enter();
	public void performStateTask();
	public void exit();
	public void transitionState();
	public StateMachine getInnerStateMachine();
	public void setInnerStateMachine(StateMachine stateMachine);
	public void runInnerStateMachine(IState state);
	
}
