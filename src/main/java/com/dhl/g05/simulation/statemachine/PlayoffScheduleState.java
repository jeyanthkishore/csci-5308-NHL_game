package com.dhl.g05.simulation.statemachine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.dhl.g05.simulation.leaguesimulation.IScheduleModel;

public class PlayoffScheduleState extends AbstractState{
	
	static final Logger logger = LogManager.getLogger(PlayoffScheduleState.class);
	private ILeague league;
	SimulationAbstractFactory simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();

	@Override
	public boolean enter() {
		logger.info("Entering into PlayoffScheduleState");
		
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
		logger.info("Exiting PlayoffScheduleState");
		
		this.setNextState(simulationFactory.createTrainingState());
		return true;
	}

}
