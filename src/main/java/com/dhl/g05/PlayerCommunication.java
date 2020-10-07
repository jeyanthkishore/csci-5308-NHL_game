package com.dhl.g05.statemachine;

import java.util.Scanner;

public class PlayerCommunication {
	private static Scanner scanner = new Scanner(System.in);
	
	
	public static void sendMessage(String message) {
		System.out.println(message);
	}
	
	
	public static String getResponse() {
		return scanner.nextLine();
	}

}
