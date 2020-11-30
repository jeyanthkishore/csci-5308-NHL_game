package com.dhl.g05.simulation.statemachine;

import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.simulation.DateHandler;
import com.dhl.g05.simulation.SimulationAbstractFactory;

public class AdvanceTimeState extends AbstractState{
	
	static final Logger logger = LogManager.getLogger(AdvanceTimeState.class);
	private ILeague league;

	@Override
	public boolean enter() {
		logger.info("Entering into AdvanceTimeState");
		
		league = this.getLeague();
		return true;
	}

	@Override
	public boolean performStateTask() {
		logger.info("Incrementing League Data by 1 day");
		
		league.incrementCurrentDate();
		return true;
	}

	@Override
	public boolean exit() {
		logger.info("Exiting AdvanceTimeState");
		
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
