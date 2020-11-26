package com.dhl.g05.communication;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.dhl.g05.coach.ICoach;
import com.dhl.g05.communication.IPlayerCommunication;
import com.dhl.g05.freeagent.IFreeAgent;

public class MockPlayerCommunication implements IPlayerCommunication{

	@Override
	public void sendMessage(String message) {
		System.out.println(message);
		System.out.println();
	}

	@Override
	public String getResponse() {
		return "yes";
	}
	
	@Override
	public String getFile() {
		return getResponse();
	}

	@Override
	public void sendMessage(List<IFreeAgent> free) {
		System.out.println("Player List Will be Displayed");
		
	}

	@Override
	public int getResponseNumber() {
		return (int) ((Math.random() * (5)));
	}

	@Override
	public void sendCoachMessage(List<ICoach> coachList) {
		System.out.println("Coach List Will be Displayed");
	}

	@Override
	public void sendManagerMessage(List<String> managerList) {
		System.out.println("Manager List Will be Displayed");
	}
	
    public void commandLineInput(String data) {
    	ByteArrayInputStream testInput = new ByteArrayInputStream(data.getBytes());
        System.setIn(testInput);
    }
	
}
