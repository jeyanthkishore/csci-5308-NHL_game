package com.dhl.g05.statemachine;

public interface IPlayerCommunication {
	public void sendMessage(String message);
	public String getResponse();
	public String getFile();
}
