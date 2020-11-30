package com.dhl.g05;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.dhl.g05.simulation.statemachine.AbstractState;

public class Driver {

	static final Logger logger = LogManager.getLogger(Driver.class);

	public static void main(String[] args) {
		logger.info("Started DHL Application");
		SimulationAbstractFactory factory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		AbstractState importState = factory.createImportState();
		factory.createStateMachine(importState).enterState();
	}

}