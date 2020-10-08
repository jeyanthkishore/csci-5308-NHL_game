package com.dhl.g05;

import java.util.Scanner;

public class PlayerCommunication implements IPlayerCommunication{
	private static Scanner scanner = new Scanner(System.in);
	
	
	public void sendMessage(String message) {
		System.out.println(message);
		System.out.println();
	}
	
	
	public String getResponse() {
		String response =  scanner.nextLine();
		System.out.println();
		return response;
	}


	@Override
	public String getFile() {
		return getResponse();
	}

}
