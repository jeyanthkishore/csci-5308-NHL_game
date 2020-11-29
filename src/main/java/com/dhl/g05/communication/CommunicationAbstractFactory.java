package com.dhl.g05.communication;

public abstract class CommunicationAbstractFactory {
	private static CommunicationAbstractFactory abstractIOFactory;

	public static CommunicationAbstractFactory getInstance(CommunicationState communication) {
		abstractIOFactory = communication.concreteMethod();
		return abstractIOFactory;
	}
	
	public abstract IPlayerCommunication getCommunication();
	
	public abstract ITradeCommunication getTradeCommunication();

}
