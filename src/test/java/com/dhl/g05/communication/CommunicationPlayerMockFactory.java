package com.dhl.g05.communication;

public class CommunicationPlayerMockFactory extends CommunicationAbstractFactory{

	@Override
	public IPlayerCommunication getCommunication() {
			return new MockPlayerCommunication();
	}

	@Override
	public ITradeCommunication getTradeCommunication() {
		return null;
	}

}
