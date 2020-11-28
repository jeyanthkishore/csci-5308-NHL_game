package com.dhl.g05.communication;

import java.util.List;
import java.util.Scanner;

import com.dhl.g05.coach.ICoach;
import com.dhl.g05.freeagent.IFreeAgent;

public class MockCreateTeamCommnication implements IPlayerCommunication{
	private Scanner scanner = new Scanner(System.in);

	@Override
	public void sendMessage(String message) {
		System.out.println(message);
		System.out.println();
	}

	@Override
	public String getResponse() {
		String response =  scanner.nextLine();
		System.out.println();
		return response;
	}

	@Override
	public int getResponseNumber() {
		return (int) ((Math.random() * (5)));
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
	public void sendCoachMessage(List<ICoach> coachList) {
		System.out.println("Coach List Will be Displayed");
		
	}

	@Override
	public void sendManagerMessage(List<String> managerList) {
		System.out.println("Manager List Will be Displayed");
	}

}
