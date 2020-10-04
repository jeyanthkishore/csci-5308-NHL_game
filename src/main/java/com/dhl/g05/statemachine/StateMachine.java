package com.dhl.g05.statemachine;

import com.dhl.g05.leagueobjects.LeagueObject;

public class StateMachine {
	
	private IState currentState;   
	private LeagueObject league;

	public StateMachine() {
		currentState = new ImportState(this);  
	}
	
	public IState getCurrentState() {
		return null;
	}

	public void setCurrentState(IState state) {
		
	}
	
	public void enterState() {

	}
	
	public void runState() {
		
	}
	
	public void exitState() {
		
	}

}
