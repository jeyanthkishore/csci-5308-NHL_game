package com.dhl.g05.statemachine;

import com.dhl.g05.league.ILeague;

public abstract class AbstractState {
	private AbstractState nextState;
	private static ILeague league;

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
}
