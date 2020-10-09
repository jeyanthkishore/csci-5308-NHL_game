package com.dhl.g05.statemachine;

import com.dhl.g05.leaguemodel.*;
import com.dhl.g05.operation.DatabaseClass;
import com.dhl.g05.operation.IDataBasePersistence;

public class StateMachine {
	
	private AbstractState currentState;   
	private IPlayerCommunication playerCommunication;
	private ILeagueModel leagueModel;
	private IDataBasePersistence database;
	
	public StateMachine() {
		currentState = new ImportState(this);  
		playerCommunication = new PlayerCommunication();
		database = new DatabaseClass();
		leagueModel = new LeagueModel(database);
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
	public void setLeague(LeagueObject league) {
		leagueModel.setLeague(league);
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
