package com.dhl.g05.communication;

public class CommunicationPlayerMockFactoryState extends CommunicationState{

	@Override
	public CommunicationAbstractFactory concreteMethod() {
		return new CommunicationPlayerMockFactory();
	}

}
