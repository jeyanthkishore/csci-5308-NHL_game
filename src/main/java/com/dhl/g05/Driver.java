package com.dhl.g05;

import com.dhl.g05.operation.DatabaseClass;
import com.dhl.g05.statemachine.LeagueModel;
import com.dhl.g05.statemachine.PlayerCommunication;
import com.dhl.g05.statemachine.StateMachine;

public class Driver {
	public static void main(String[] args) {
		StateMachine stateMachine = new StateMachine(new PlayerCommunication(), new LeagueModel(new DatabaseClass())); 
		stateMachine.enterState();
	}
}
