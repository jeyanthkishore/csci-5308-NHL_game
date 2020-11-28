package com.dhl.g05;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dhl.g05.gameplayconfig.AbstractGamePlayConfigFactory;
import com.dhl.g05.gameplayconfig.GamePlayConfigFactory;
import com.dhl.g05.player.AbstractPlayerFactory;
import com.dhl.g05.player.PlayerFactory;
import com.dhl.g05.statemachine.AbstractState;
import com.dhl.g05.statemachine.StateMachineAbstractFactory;

public class Driver {

	static final Logger logger = LogManager.getLogger(Driver.class);

	public static void main(String[] args) {
		logger.info("Started DHL Application");
		AbstractGamePlayConfigFactory.setFactory(new GamePlayConfigFactory());
		AbstractPlayerFactory.setFactory(new PlayerFactory());
		StateMachineAbstractFactory factory = ApplicationConfiguration.instance().getStateMachineConcreteFactoryState();
		AbstractState importState = factory.createImportState();
		factory.createStateMachine(importState).enterState();
	}

}