package com.dhl.g05.statemachine.mocks;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import com.dhl.g05.leaguemodel.CoachObject;
import com.dhl.g05.leaguemodel.FreeAgentObject;
import com.dhl.g05.statemachine.IPlayerCommunication;

public class MockPlayerCommunication implements IPlayerCommunication{

	@Override
	public void sendMessage(String message) {
		System.out.println(message);
		System.out.println();
	}

	@Override
	public String getResponse() {
		return "mock response";
	}
	
	@Override
	public String getFile() {
		return "src/test/java/com/dhl/g05/jsontestfiles/jsonGoodInfo.json";
	}

	@Override
	public void sendMessage(List<FreeAgentObject> free) {
		System.out.println("Player Details Will be Displayed");
	}

	@Override
	public int getResponseNumber() {
		Random rand = new Random();
		int number =  rand.nextInt(4);
		if (number==0){          
			number= number+1;
		}
		return number;
	}

	@Override
	public void sendCoachMessage(List<CoachObject> coachList) {
		System.out.println("Coach List Will be Displayed");
	}
}
