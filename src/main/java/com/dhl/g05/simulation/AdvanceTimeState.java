package com.dhl.g05.simulation;

import java.time.LocalDate;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.ILeague;

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
		SimulationAbstractFactory stateFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		if(DateHandler.getInstance().isRegularSeasonEndDate(currentDate)) {
			this.setNextState(stateFactory.createPlayOffState());
		}else {
			this.setNextState(stateFactory.createTrainingState());
		}
		return true;
	}

}
