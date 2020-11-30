package com.dhl.g05.simulation.statemachine;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.communication.IPlayerCommunication;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.simulation.ILeagueCreator;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.mysql.cj.util.StringUtils;

public class ImportState extends AbstractState {
	private IPlayerCommunication communication;
	private ILeagueCreator creator;
	private String fileName;

	public ImportState(IPlayerCommunication communication) {
		this.communication = communication;
	}

	@Override
	public boolean enter() {
		communication.sendMessage(StateMachineConstant.ImportStart.getValue());
		fileName = communication.getFile();
		return true;
	}

	@Override
	public boolean performStateTask() {
		SimulationAbstractFactory simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		if (StringUtils.isNullOrEmpty(fileName)) {
			return true;
		} 

		creator = simulationFactory.createLeagueCreator();
		ILeague league = creator.createLeagueFromFile(fileName);
		if (league == null) {
			this.setLeague(null);
			communication.sendMessage(StateMachineConstant.LeagueCreationIssue.getValue());
		} else {
			this.setLeague(league);
			return true;
		}

		return false;

	}

	@Override
	public boolean exit() {
		SimulationAbstractFactory stateFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		if (StringUtils.isNullOrEmpty(fileName)) {
			this.setNextState(stateFactory.createLoadTeamState()); 
		} else {
			this.setNextState(stateFactory.createCreateTeamState());
		}
		return true;
	}

}
