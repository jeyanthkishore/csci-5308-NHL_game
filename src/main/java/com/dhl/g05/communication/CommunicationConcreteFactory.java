package com.dhl.g05.communication;

public class CommunicationConcreteFactory extends CommunicationAbstractFactory {

	@Override
	public IPlayerCommunication getCommunication() {
		return new PlayerCommunication();
	}

	@Override
	public ITradeCommunication getTradeCommunication() {
		return new PlayerCommunication();
	}
}
