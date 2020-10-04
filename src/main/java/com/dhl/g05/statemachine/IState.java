package com.dhl.g05.statemachine;

public interface IState {
	
	public void enter();
	public void performStateTask();
	public void exit();
	public void transitionState(IState state);
	public StateMachine getInnerStateMachine();
	public void setInnerStateMachine(StateMachine stateMachine);
	public void runInnerStateMachine();
	public boolean didEnterState();
	public boolean didRunState();
	public boolean didExitState();
	
}
