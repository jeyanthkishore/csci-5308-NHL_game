package com.dhl.g05.statemachine;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.leaguesimulation.IInitializeSchedule;
import com.dhl.g05.leaguesimulation.ScheduleModel;

public class PlayoffScheduleState extends AbstractState{
	private ILeague league;

	@Override
	public boolean enter() {
		league = this.getLeague();
		return true;
	}

	@Override
	public boolean performStateTask() {
		IInitializeSchedule leagueSchedule = new ScheduleModel();
		leagueSchedule.generatePlayOff(league, league.getLeagueStanding());
		return true;
	}

	@Override
	public boolean exit() {
		StateMachineAbstractFactory stateFactory = ApplicationConfiguration.instance().getStateMachineFactoryState();
		this.setNextState(stateFactory.getTrainingState());
		return true;
	}

}
