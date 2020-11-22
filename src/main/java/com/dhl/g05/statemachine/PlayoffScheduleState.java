package com.dhl.g05.statemachine;

import com.dhl.g05.league.LeagueModel;

public class PlayoffScheduleState extends AbstractState{
	private LeagueModel league;

	@Override
	public boolean enter() {
		league = this.getLeague();
		return true;
	}

	@Override
	public boolean performStateTask() {
		league.getLeagueSchedule().generatePlayoffSchedule(league, league.getLeagueStanding());
		return true;
	}

	@Override
	public boolean exit() {
		this.setNextState(AbstractStateMachineFactory.getFactory().getTrainingState());
		return true;
	}

}
