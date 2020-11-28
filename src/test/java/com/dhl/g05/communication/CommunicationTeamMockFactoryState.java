package com.dhl.g05.communication;

public class CommunicationTeamMockFactoryState extends CommunicationConcreteFactoryState {

	@Override
	public CommunicationAbstractFactory concreteMethod() {
		return new CommunicationTeamMockFactory();
	}

}
