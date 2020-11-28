package com.dhl.g05.communication;

public class CommunicationConcreteFactoryState extends CommunicationState {

	@Override
	public CommunicationAbstractFactory concreteMethod() {
		return new CommunicationConcreteFactory();
	}

}
