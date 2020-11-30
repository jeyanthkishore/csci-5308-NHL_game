package com.dhl.g05.communication;

public class CommunicationTeamOperationMockFactoryState extends CommunicationConcreteFactoryState {

	@Override
	public CommunicationAbstractFactory concreteMethod() {
		return new CommunicationTeamOperationMockFactory();
	}

}
