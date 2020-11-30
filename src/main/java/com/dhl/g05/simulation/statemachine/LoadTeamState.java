package com.dhl.g05.simulation.statemachine;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.communication.IPlayerCommunication;
import com.dhl.g05.database.DatabaseAbstractFactory;
import com.dhl.g05.database.IDeserializeModel;
import com.dhl.g05.database.ITeamDatabaseOperation;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.model.ModelAbstractFactory;
import com.dhl.g05.simulation.SimulationAbstractFactory;

public class LoadTeamState extends AbstractState{

	private IPlayerCommunication communication;
	private ILeague league;
	private String teamName;

	public LoadTeamState(IPlayerCommunication communicate) {
		this.communication = communicate;
	}

	@Override
	public boolean enter() {
		ModelAbstractFactory modelFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState(); 
		league = modelFactory.createLeagueModel();
		communication.sendMessage(StateMachineConstant.EnterTeam.getValue());
		teamName = communication.getResponse();

		return true;
	}

	@Override
	public boolean performStateTask() {
		ModelAbstractFactory modelFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();
		DatabaseAbstractFactory databaseFactory = ApplicationConfiguration.instance().getDatabaseConcreteFactoryState();
		ITeam team = modelFactory.createTeamModel();
		ITeamDatabaseOperation checkTeam = databaseFactory.createTeamDatabaseOperation();
		IDeserializeModel loadLeague = databaseFactory.createDeserializeObject();
		if (team.isTeamExist(teamName,checkTeam)) {
			ILeague loadedLeague = league.loadLeagueObject(loadLeague,teamName);
			this.setLeague(loadedLeague);
			return true;
		} else {
			communication.sendMessage(StateMachineConstant.NoTeam.getValue());
			return false;
		}
	}

	@Override
	public boolean exit() {
		SimulationAbstractFactory stateFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		this.setNextState(stateFactory.createPlayerChoiceState());
		return true;
	}

}
