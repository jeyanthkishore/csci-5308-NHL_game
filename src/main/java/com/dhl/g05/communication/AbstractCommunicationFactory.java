package com.dhl.g05.communication;

public abstract class AbstractCommunicationFactory {
	private static AbstractCommunicationFactory abstractIOFactory;

	public static AbstractCommunicationFactory getFactory() {
		return abstractIOFactory;
	}

	public static void setFactory(AbstractCommunicationFactory ioFactory) {
		abstractIOFactory = ioFactory;
	}
	
	public abstract IPlayerCommunication getCommunication();

}
