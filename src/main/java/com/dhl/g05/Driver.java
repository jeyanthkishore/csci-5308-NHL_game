package com.dhl.g05;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dhl.g05.model.AbstractPlayerFactory;
import com.dhl.g05.model.PlayerFactory;
import com.dhl.g05.simulation.AbstractState;
import com.dhl.g05.simulation.StateMachineAbstractFactory;

public class Driver {

	static final Logger logger = LogManager.getLogger(Driver.class);

	public static void main(String[] args) {
		logger.info("Started DHL Application");
		AbstractPlayerFactory.setFactory(new PlayerFactory());
		StateMachineAbstractFactory factory = ApplicationConfiguration.instance().getStateMachineConcreteFactoryState();
		AbstractState importState = factory.createImportState();
		factory.createStateMachine(importState).enterState();
	}

}