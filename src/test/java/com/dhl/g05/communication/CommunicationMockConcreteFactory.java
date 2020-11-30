package com.dhl.g05.communication;

public class CommunicationMockConcreteFactory extends CommunicationMockAbstractFactory{

	@Override
	public CommunicationTeamOperationMockFactoryState createMockTeamCommunicationState() {
		return new CommunicationTeamOperationMockFactoryState();
	}

	@Override
	public CommunicationPlayerOperationMockFactoryState createMockPlayerCommunicationState() {
		return new CommunicationPlayerOperationMockFactoryState();
	}

}
