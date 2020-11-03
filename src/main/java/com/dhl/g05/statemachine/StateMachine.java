package com.dhl.g05.statemachine;

import com.dhl.g05.communication.IPlayerCommunication;

public class StateMachine {

	private AbstractState currentState;   
	private IPlayerCommunication playerCommunication;
	private ILeagueModelJson leagueModel;

	public StateMachine(IPlayerCommunication playerCommunication, ILeagueModelJson leagueModel) {
		currentState = new ImportState(this);  
		this.playerCommunication = playerCommunication;
		this.leagueModel = leagueModel;
	}

	public AbstractState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(AbstractState state) {
		currentState = state;
	}

	public boolean enterState() {
		if (currentState.enter()) {
			return runState();
		} else {
			enterState();
		}
		return false;
	}

	public boolean runState() {
		if (currentState.performStateTask()) {
			return exitState();
		} else {
			enterState();
		}
		return false;
	}

	public boolean exitState() {

		if (currentState.exit()) {
			currentState = currentState.getNextState();
			if (currentState!=null) {
				return enterState();
			} else {
				this.exit();
			}
			return true;
		}
		return false;
	}

	public void exit() {
		if(leagueModel.getLeague()!=null) {
			leagueModel.persistLeague();
		}
	}

	public void setPlayerCommunication(IPlayerCommunication playerCommunication) {
		this.playerCommunication = playerCommunication;

	}

	public IPlayerCommunication getPlayerCommunication() {
		return playerCommunication;
	}

	public void setLeagueModel(ILeagueModelJson leagueModel) {
		this.leagueModel = leagueModel;
	}

	public ILeagueModelJson getLeagueModel() {
		return leagueModel;
	}
}
