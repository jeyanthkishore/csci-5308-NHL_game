package com.dhl.g05.communication;

public class CommunicationTeamMockFactory extends CommunicationAbstractFactory{

	@Override
	public IPlayerCommunication getCommunication() {
			return new MockCreateTeamCommnication();
	}

}