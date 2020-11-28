package com.dhl.g05.simulation;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.communication.CommunicationAbstractFactory;

public class StateMachineConcreteFactoryState extends StateMachineState{

	@Override
	public StateMachineAbstractFactory concreteMethod() {
		CommunicationAbstractFactory communicationState = ApplicationConfiguration.instance().getCommunicationConcreteFactoryState();
		return new StateMachineConcreteFactory(communicationState.getCommunication());
	}

}
