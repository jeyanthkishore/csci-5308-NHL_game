package com.dhl.g05.statemachine;

import com.dhl.g05.league.LeagueModel;

public abstract class AbstractState {
	private AbstractState nextState;
	private static LeagueModel league;

	public abstract boolean enter();
	public abstract boolean performStateTask();
	public abstract boolean exit();


	public void setNextState(AbstractState state) {
		this.nextState = state;
	}

	public AbstractState getNextState() {
		return nextState;
	}

	public void setLeague(LeagueModel league) {
		AbstractState.league = league;
	}

	public LeagueModel getLeague() {
		return	league;
	}
}
