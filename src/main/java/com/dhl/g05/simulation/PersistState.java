package com.dhl.g05.simulation;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.database.DatabaseAbstractFactory;
import com.dhl.g05.database.ISerializeModel;
import com.dhl.g05.model.ILeague;

public class PersistState extends AbstractState {
	private ILeague league;
	
	@Override
	public boolean enter() {
		league = this.getLeague();
		return true;
	}

	@Override
	public boolean performStateTask() {
		DatabaseAbstractFactory database = ApplicationConfiguration.instance().getDatabaseConcreteFactoryState();
		ISerializeModel saveLeague = database.createSerializeObject();
		String teamName = this.getCurrentUserTeam();
		league.saveLeagueObject(saveLeague,teamName);
		return true;
	}

	@Override
	public boolean exit() {
		StateMachineAbstractFactory stateFactory = ApplicationConfiguration.instance().getStateMachineConcreteFactoryState();
		if(league.getLeagueSchedule().isStanleyCupWinnerDetermined()) {
			this.setNextState(null);
		} else {
			this.setNextState(stateFactory.createAdvancedTimeState());
		}
		return true;
	}

}
