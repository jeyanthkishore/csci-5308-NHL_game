package com.dhl.g05.statemachine;

import com.dhl.g05.leagueobjects.LeagueObject;

public class StateMachine {
	
	private IState currentState;   
	private LeagueObject league;

	public StateMachine() {
		currentState = new ImportState(this);  
	}
	
	public IState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(IState state) {
		currentState = state;
	}
	
	public void enterState() {
		currentState.enter();
	}
	
	public void runState() {
		currentState.performStateTask();
	}
	
	public void exitState() {
		currentState.exit();
	}

}
