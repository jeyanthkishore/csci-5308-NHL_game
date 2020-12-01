package com.dhl.g05.communication;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Scanner;

import com.dhl.g05.model.IFreeAgent;
import com.dhl.g05.model.IPlayer;

public class MockTradeCommunication implements ITradeCommunication {
	private Scanner scanner;

	@Override
	public void sendTradeMessage(List<IPlayer> list, List<IPlayer> list2) {
		System.out.println("Display the players offered and requested for the User Team");
	}

	@Override
	public int getTradeDecision() {
		scanner = new Scanner(System.in);
		int response = scanner.nextInt();
		return response;
	}

	@Override
	public String getResponse() {
		scanner = new Scanner(System.in);
		String response = scanner.nextLine();
		return response;
	}

	@Override
	public void sendMessage(String message) {
		System.out.println("Display the message");
		
	}

	@Override
	public void DropPlayerDetails(List<IPlayer> players, int count) {
		System.out.println("Display the players that should be droped from the User Team");
	}

	@Override
	public void AddPlayerDetails(List<IFreeAgent> freeAgents, int count) {
		System.out.println("Display the players that should be added from the User Team");	
	}
	
	public void commandLineInput(String data) {
		ByteArrayInputStream testInput = new ByteArrayInputStream(data.getBytes());
		System.setIn(testInput);
	}

}
