package com.dhl.g05.simulation.statemachine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.database.DatabaseAbstractFactory;
import com.dhl.g05.database.ISerializeModel;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.simulation.SimulationAbstractFactory;

public class PersistState extends AbstractState {
	
	static final Logger logger = LogManager.getLogger(PersistState.class);
	private ILeague league;
	
	@Override
	public boolean enter() {
		logger.info("Entering into PersistState");
		
		league = this.getLeague();
		return true;
	}

	@Override
	public boolean performStateTask() {
		logger.info("Saving current model in the memory");
		
		DatabaseAbstractFactory database = ApplicationConfiguration.instance().getDatabaseConcreteFactoryState();
		ISerializeModel saveLeague = database.createSerializeObject();
		String teamName = this.getCurrentUserTeam();
		league.saveLeagueObject(saveLeague,teamName);
		return true;
	}

	@Override
	public boolean exit() {
		logger.info("Exiting PersistState");
		
		SimulationAbstractFactory simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		if(league.getLeagueSchedule().isStanleyCupWinnerDetermined()) {
			this.setNextState(null);
		} else {
			this.setNextState(simulationFactory.createAdvancedTimeState());
		}
		return true;
	}

}
