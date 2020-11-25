package com.dhl.g05.statemachine;

import com.dhl.g05.database.AbstractDataBaseFactory;
import com.dhl.g05.database.ISerializeModel;
import com.dhl.g05.league.ILeague;

public class PersistState extends AbstractState {
	private ILeague league;
	
	@Override
	public boolean enter() {
		league = this.getLeague();
		return true;
	}

	@Override
	public boolean performStateTask() {
		ISerializeModel saveLeague = AbstractDataBaseFactory.getFactory().getSerializeModel();
		String teamName = this.getCurrentUserTeam();
		league.saveLeagueObject(saveLeague,teamName);
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
