package com.dhl.g05.statemachine;

import com.dhl.g05.leagueobjects.LeagueObject;

public class StateMachine {
	
	private AbstractState currentState;   
	private LeagueObject league;

	public StateMachine() {
		currentState = new ImportState(this);  
	}
	
	public AbstractState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(AbstractState state) {
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
	
	public void setLeague(LeagueObject league) {
		
	}
	
	public LeagueObject getLeague() {
		return league;
	}

}
