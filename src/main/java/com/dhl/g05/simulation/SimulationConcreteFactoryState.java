package com.dhl.g05.simulation;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.communication.CommunicationAbstractFactory;

public class SimulationConcreteFactoryState extends SimulationState{

	@Override
	public SimulationAbstractFactory concreteMethod() {
		CommunicationAbstractFactory communicationState = ApplicationConfiguration.instance().getCommunicationConcreteFactoryState();
		return new SimulationConcreteFactory(communicationState.getCommunication());
	}

}
