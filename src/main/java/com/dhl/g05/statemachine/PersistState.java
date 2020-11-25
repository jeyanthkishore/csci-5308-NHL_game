package com.dhl.g05.statemachine;

import com.dhl.g05.db.AbstractDataBaseFactory;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.league.ILeagueModelPersistence;

public class PersistState extends AbstractState {
	private ILeague league;
	
	@Override
	public boolean enter() {
		league = this.getLeague();
		return true;
	}

	@Override
	public boolean performStateTask() {
		ILeagueModelPersistence saveLeague = AbstractDataBaseFactory.getFactory().getLeagueDatabase();
		league.saveLeagueObject(saveLeague);
		return true;
	}

	@Override
	public boolean exit() {
		if(league.getLeagueSchedule().isStanleyCupWinnerDetermined()) {
			this.setNextState(null);
		} else {
			this.setNextState(AbstractStateMachineFactory.getFactory().getAdvancedTimeState());
		}
		return true;
	}

}
