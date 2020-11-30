package com.dhl.g05.communication;

public class CommunicationPlayerOperationMockFactory extends CommunicationAbstractFactory{

	@Override
	public IPlayerCommunication getCommunication() {
			return new MockPlayerCommunication();
	}

	@Override
	public ITradeCommunication getTradeCommunication() {
		return null;
	}

}
