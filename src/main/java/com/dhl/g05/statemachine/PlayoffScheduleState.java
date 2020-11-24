package com.dhl.g05.statemachine;

import com.dhl.g05.league.ILeague;
import com.dhl.g05.league.LeagueModel;
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
		this.setNextState(AbstractStateMachineFactory.getFactory().getTrainingState());
		return true;
	}

}
