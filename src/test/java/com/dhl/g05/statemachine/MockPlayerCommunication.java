package com.dhl.g05.statemachine;

public class MockPlayerCommunication implements IPlayerCommunication{

	@Override
	public void sendMessage(String message) {
		//do nothing
	}

	@Override
	public String getResponse() {
		return "mock response";
	}
	
	@Override
	public String getFile() {
		return "src/test/java/com/dhl/g05/jsontestfiles/jsonGoodInfo.json";
	}

}
