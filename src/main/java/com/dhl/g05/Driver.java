package com.dhl.g05;

import com.dhl.g05.communication.PlayerCommunication;
import com.dhl.g05.statemachine.LeagueModelJson;
import com.dhl.g05.statemachine.StateMachine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Driver {
	static final Logger logger = LogManager.getLogger(Driver.class);
	public static void main(String[] args) {
		logger.info("Test-Driver-Info");
		logger.error("Test-Driver-Error");
		logger.debug("Test-Driver-Debug");
		StateMachine stateMachine = new StateMachine(new PlayerCommunication(), new LeagueModelJson());
		stateMachine.enterState();
	}
}
