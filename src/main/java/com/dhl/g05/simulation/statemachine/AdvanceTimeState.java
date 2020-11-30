package com.dhl.g05.simulation.statemachine;

import java.time.LocalDate;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.simulation.DateHandler;
import com.dhl.g05.simulation.SimulationAbstractFactory;

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
		SimulationAbstractFactory simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		if(DateHandler.instance().isRegularSeasonEndDate(currentDate)) {
			this.setNextState(simulationFactory.createPlayOffState());
		}else {
			this.setNextState(simulationFactory.createTrainingState());
		}
		return true;
	}

}
