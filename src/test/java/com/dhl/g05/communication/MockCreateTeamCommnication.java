package com.dhl.g05.communication;

import java.util.List;
import java.util.Scanner;

import com.dhl.g05.model.ICoach;
import com.dhl.g05.model.IFreeAgent;

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
	public void displayFreeAgentList(List<IFreeAgent> free) {
		System.out.println("Player List Will be Displayed");
		
	}

	@Override
	public void displayCoachList(List<ICoach> coachList) {
		System.out.println("Coach List Will be Displayed");
		
	}

	@Override
	public void displayManagerList(List<String> managerList) {
		System.out.println("Manager List Will be Displayed");
	}

}
