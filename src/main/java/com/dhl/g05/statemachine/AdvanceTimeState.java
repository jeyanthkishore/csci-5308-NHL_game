package com.dhl.g05.statemachine;

import java.time.LocalDate;

import com.dhl.g05.league.ILeague;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.leaguesimulation.DateHandler;

public class AdvanceTimeState extends AbstractState{
	private ILeague league;

	@Override
	public boolean enter() {
		league = this.getLeague();
		return true;
	}

	@Override
	public boolean performStateTask() {
		league.incrementCurrentDate();
		return true;
	}

	@Override
	public boolean exit() {
		LocalDate currentDate = league.getLeagueCurrentDate();
		if(DateHandler.getInstance().isRegularSeasonEndDate(currentDate)) {
			this.setNextState(AbstractStateMachineFactory.getFactory().getPlayOffState());
		}else {
			this.setNextState(AbstractStateMachineFactory.getFactory().getTrainingState());
		}
		return true;
	}

}
