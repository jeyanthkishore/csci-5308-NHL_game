package com.dhl.g05.statemachine.mocks;

import java.util.List;

import com.dhl.g05.coach.CoachModel;
import com.dhl.g05.communication.IPlayerCommunication;
import com.dhl.g05.freeagent.FreeAgentModel;
import com.dhl.g05.player.IRandomGeneratorFactory;
import com.dhl.g05.player.RandomGeneratorFactory;

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
		return "src/test/java/com/dhl/g05/jsontestfiles/jsonGoodInfo.json";
	}

	@Override
	public void sendMessage(List<FreeAgentModel> free) {
		System.out.println("Player List Will be Displayed");
		
	}

	@Override
	public int getResponseNumber() {
		IRandomGeneratorFactory randomGeneratorFactory = new RandomGeneratorFactory();
		int randomValue = randomGeneratorFactory.getRandomIntegerNumber(0, 22);
		return randomValue;
	}

	@Override
	public void sendCoachMessage(List<CoachModel> coachList) {
		System.out.println("Coach List Will be Displayed");
	}

	@Override
	public void sendManagerMessage(List<String> managerList) {
		System.out.println("Manager List Will be Displayed");
		
	}
}
