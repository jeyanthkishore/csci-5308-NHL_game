package com.dhl.g05.statemachine;

import com.dhl.g05.ILeagueModel;
import com.dhl.g05.IPlayerCommunication;
import com.dhl.g05.LeagueModel;
import com.dhl.g05.PlayerCommunication;
import com.dhl.g05.leagueobjects.LeagueObject;

public class StateMachine {
	
	private AbstractState currentState;   
	private IPlayerCommunication playerCommunication;
	private ILeagueModel leagueModel;

	public StateMachine() {
		currentState = new ImportState(this);  
		playerCommunication = new PlayerCommunication();
		leagueModel = new LeagueModel();
	}
	
	public AbstractState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(AbstractState state) {
		currentState = state;
	}
	
	public void enterState() {
		currentState.enter();
	}
	
	public void runState() {
		currentState.performStateTask();
	}
	
	public void exitState() {
		currentState.exit();
	}
	
	public void setLeague(LeagueObject league) {
		
	}
	
	public LeagueObject getLeague() {
		return leagueModel.getLeague();
	}

	
	public void setPlayerCommunication(IPlayerCommunication playerCommunication) {
		this.playerCommunication = playerCommunication;
		
	}
	
	public IPlayerCommunication getPlayerCommunication() {
		return playerCommunication;
	}
	
	public void setLeagueModel(ILeagueModel leagueModel) {
		this.leagueModel = leagueModel;
	}
	
	public ILeagueModel getLeagueModel() {
		return leagueModel;
	}
}
