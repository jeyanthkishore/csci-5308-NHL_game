package com.dhl.g05.communication;

public class CommunicationFactory extends AbstractCommunicationFactory{

	@Override
	public IPlayerCommunication getCommunication() {
		return new PlayerCommunication();
	}

}
