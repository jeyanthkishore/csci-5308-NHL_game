package com.dhl.g05.simulation;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.ILeague;

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
		StateMachineAbstractFactory stateFactory = ApplicationConfiguration.instance().getStateMachineConcreteFactoryState();
		this.setNextState(stateFactory.createTrainingState());
		return true;
	}

}
