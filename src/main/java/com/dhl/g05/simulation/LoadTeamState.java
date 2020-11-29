package com.dhl.g05.simulation;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.communication.IPlayerCommunication;
import com.dhl.g05.database.DatabaseAbstractFactory;
import com.dhl.g05.database.ITeamDatabaseOperation;
import com.dhl.g05.database.IDeserializeModel;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.model.ModelAbstractFactory;
import com.dhl.g05.model.TeamModel;

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
		communication.sendMessage("Enter team name:");
		teamName = communication.getResponse();

		return true;
	}

	@Override
	public boolean performStateTask() {
		ITeam team = new TeamModel();
		DatabaseAbstractFactory databaseFactory = ApplicationConfiguration.instance().getDatabaseConcreteFactoryState();
		ITeamDatabaseOperation checkTeam = databaseFactory.createTeamDatabaseOperation();
		IDeserializeModel loadLeague = databaseFactory.createDeserializeObject();
		if (team.isTeamExist(teamName,checkTeam)) {
			ILeague loadedLeague = league.loadLeagueObject(loadLeague,teamName);
			this.setLeague(loadedLeague);
			return true;
		} else {
			communication.sendMessage("Team does not exist");
			return false;
		}
	}

	@Override
	public boolean exit() {
		SimulationAbstractFactory stateFactory = ApplicationConfiguration.instance().getStateMachineConcreteFactoryState();
		this.setNextState(stateFactory.createPlayerChoiceState());
		return true;
	}

}
