package com.dhl.g05.simulation.statemachine;

import com.dhl.g05.model.ILeague;

public abstract class AbstractState {
	private AbstractState nextState;
	private static ILeague league;
	private static String currentUserTeam;

	public abstract boolean enter();
	public abstract boolean performStateTask();
	public abstract boolean exit();


	public void setNextState(AbstractState state) {
		this.nextState = state;
	}

	public AbstractState getNextState() {
		return nextState;
	}

	public void setLeague(ILeague league) {
		AbstractState.league = league;
	}

	public ILeague getLeague() {
		return	league;
	}
	
	public void setCurrentUserTeam(String teamName) {
		AbstractState.currentUserTeam = teamName;
	}
	
	public String getCurrentUserTeam() {
		return currentUserTeam;
	}
}
