package com.dhl.g05.statemachine;

import com.dhl.g05.communication.IPlayerCommunication;
import com.dhl.g05.filehandler.ILeagueModelJson;

public class LoadTeamState extends AbstractState{

	private IPlayerCommunication communication;
	private ILeagueModelJson leagueModel;
	private String teamName;

	public LoadTeamState(IPlayerCommunication communicate, ILeagueModelJson leagueModel) {
		this.communication = communicate;
		this.leagueModel = leagueModel;
	}

	@Override
	public boolean enter() {

		communication.sendMessage("Enter team name:");
		teamName = communication.getResponse();

		return true;
	}

	@Override
	public boolean performStateTask() {
		//Team Name Check
		//League Object load team
//		if (leagueModel.loadTeam(teamName)) {
			return true;
//		} else {
//			communication.sendMessage("Team does not exist");
//			return false;
//		}
	}

	@Override
	public boolean exit() {
		this.setNextState(AbstractStateMachineFactory.getFactory().getPlayerChoiceState());
		return true;
	}

}
