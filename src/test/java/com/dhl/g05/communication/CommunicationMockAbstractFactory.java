package com.dhl.g05.communication;

public abstract class CommunicationMockAbstractFactory {

	private static CommunicationMockAbstractFactory communicationMockAbstractFactory;

	public static CommunicationMockAbstractFactory instance(CommunicationMockState state) {
		communicationMockAbstractFactory = state.concreteMethod();
		return communicationMockAbstractFactory;
	}
	
	public abstract CommunicationTeamOperationMockFactoryState createMockTeamCommunicationState();
	public abstract CommunicationPlayerOperationMockFactoryState createMockPlayerCommunicationState();
	
}
