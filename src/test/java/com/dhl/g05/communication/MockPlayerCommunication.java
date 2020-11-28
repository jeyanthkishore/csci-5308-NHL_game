package com.dhl.g05.communication;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Scanner;

import com.dhl.g05.model.ICoach;
import com.dhl.g05.model.IFreeAgent;

public class MockPlayerCommunication implements IPlayerCommunication{

	private Scanner scanner;

	public void sendMessage(String message) {
		System.out.println(message);
		System.out.println();
	}

	public String getResponse() {
		scanner = new Scanner(System.in);
		String response = scanner.nextLine();
		System.out.println();
		return response;
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
		scanner = new Scanner(System.in);
		int response = scanner.nextInt();
		System.out.println();
		return response;
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
