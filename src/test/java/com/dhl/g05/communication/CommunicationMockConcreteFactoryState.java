package com.dhl.g05.communication;

public class CommunicationMockConcreteFactoryState extends CommunicationMockState{

	@Override
	public CommunicationMockAbstractFactory concreteMethod() {
		return new CommunicationMockConcreteFactory();
	}

}
