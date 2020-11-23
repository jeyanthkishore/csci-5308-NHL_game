package com.dhl.g05.statemachine;

import com.dhl.g05.league.ILeague;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.leaguesimulation.leagueschedule.IInitializeSchedule;
import com.dhl.g05.leaguesimulation.leagueschedule.Schedule;

public class PlayoffScheduleState extends AbstractState{
	private ILeague league;

	@Override
	public boolean enter() {
		league = this.getLeague();
		return true;
	}

	@Override
	public boolean performStateTask() {
		IInitializeSchedule leagueSchedule = new Schedule();
		leagueSchedule.generatePlayOff(league, league.getLeagueStanding());
		return true;
	}

	@Override
	public boolean exit() {
		this.setNextState(AbstractStateMachineFactory.getFactory().getTrainingState());
		return true;
	}

}
