package com.dhl.g05.statemachine;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.communication.CommunicationAbstractFactory;

public class StateMachineFactoryState extends StateMachineState{

	@Override
	public StateMachineAbstractFactory concreteMethod() {
		CommunicationAbstractFactory communicationState = ApplicationConfiguration.instance().getCommunicationConcreteFactoryState();
		return new StateMachineFactory(communicationState.getCommunication());
	}

}
