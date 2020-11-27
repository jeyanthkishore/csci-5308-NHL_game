package com.dhl.g05.statemachine;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.database.DatabaseAbstractFactory;
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
		DatabaseAbstractFactory database = ApplicationConfiguration.instance().getDatabaseFactoryState();
		ISerializeModel saveLeague = database.getSerializeModel();
		String teamName = this.getCurrentUserTeam();
		league.saveLeagueObject(saveLeague,teamName);
		return true;
	}

	@Override
	public boolean exit() {
		StateMachineAbstractFactory stateFactory = ApplicationConfiguration.instance().getStateMachineFactoryState();
		if(league.getLeagueSchedule().isStanleyCupWinnerDetermined()) {
			this.setNextState(null);
		} else {
			this.setNextState(stateFactory.getAdvancedTimeState());
		}
		return true;
	}

}
