package com.dhl.g05.communication;

public class CommunicationFactoryState extends CommunicationState {

	@Override
	public CommunicationAbstractFactory concreteMethod() {
		return new CommunicationFactory();
	}

}
