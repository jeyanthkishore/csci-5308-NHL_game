package com.dhl.g05.communication;

public class CommunicationTeamMockFactoryState extends CommunicationFactoryState {

	@Override
	public CommunicationAbstractFactory concreteMethod() {
		return new CommunicationTeamMockFactory();
	}

}
