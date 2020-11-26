package com.dhl.g05.communication;

public class CommunicationFactory extends CommunicationAbstractFactory{

	@Override
	public IPlayerCommunication getCommunication() {
		return new PlayerCommunication();
	}

}
