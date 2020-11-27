package com.dhl.g05.statemachine;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.communication.IPlayerCommunication;
import com.dhl.g05.database.DatabaseAbstractFactory;
import com.dhl.g05.database.ICheckTeam;
import com.dhl.g05.database.IDeserializeModel;
import com.dhl.g05.database.ISerializeModel;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.team.ITeam;
import com.dhl.g05.team.TeamModel;

public class LoadTeamState extends AbstractState{

	private IPlayerCommunication communication;
	private ILeague league;
	private String teamName;

	public LoadTeamState(IPlayerCommunication communicate) {
		this.communication = communicate;
	}

	@Override
	public boolean enter() {
		league = this.getLeague();
		communication.sendMessage("Enter team name:");
		teamName = communication.getResponse();

		return true;
	}

	@Override
	public boolean performStateTask() {
		//Team Name Check
		//League Object load team
//		ITeam team = new TeamModel();
//		ICheckTeam checkTeam = AbstractDataBaseFactory.getFactory().getCheckTeam();
//		IDeserializeModel loadLeague = AbstractDataBaseFactory.getFactory().getDeserializeModel();
//		if (team.isTeamExist(teamName,checkTeam)) {
//			league.loadLeagueObject(loadLeague,teamName);
			return true;
//		} else {
//			communication.sendMessage("Team does not exist");
//			return false;
//		}
	}

	@Override
	public boolean exit() {
		StateMachineAbstractFactory stateFactory = ApplicationConfiguration.instance().getStateMachineFactoryState();
		this.setNextState(stateFactory.getPlayerChoiceState());
		return true;
	}

}
