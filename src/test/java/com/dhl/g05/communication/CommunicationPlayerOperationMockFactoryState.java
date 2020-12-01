package com.dhl.g05.communication;

public class CommunicationPlayerOperationMockFactoryState extends CommunicationState{

	@Override
	public CommunicationAbstractFactory concreteMethod() {
		return new CommunicationPlayerOperationMockFactory();
	}

}
