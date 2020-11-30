package com.dhl.g05.simulation.statemachine;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.dhl.g05.simulation.leaguesimulation.IScheduleModel;

public class PlayoffScheduleState extends AbstractState{
	private ILeague league;
	SimulationAbstractFactory simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();

	@Override
	public boolean enter() {
		league = this.getLeague();
		return true;
	}

	@Override
	public boolean performStateTask() {
		IScheduleModel leagueSchedule = simulationFactory.createScheduleModel();
		leagueSchedule.generatePlayOff(league, league.getLeagueStanding());
		return true;
	}

	@Override
	public boolean exit() {
		this.setNextState(simulationFactory.createTrainingState());
		return true;
	}

}
