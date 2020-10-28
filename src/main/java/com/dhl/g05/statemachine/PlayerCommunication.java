package com.dhl.g05.statemachine;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import com.dhl.g05.leaguemodel.coach.CoachModel;
import com.dhl.g05.leaguemodel.freeagent.FreeAgentModel;

public class PlayerCommunication implements IPlayerCommunication{
	private static Scanner scanner = new Scanner(System.in);
	
	
	public void sendMessage(String message) {
		System.out.println(message);
		System.out.println();
	}
	
	
	public String getResponse() {
		scanner = new Scanner(System.in);
		String response =  scanner.nextLine();
		System.out.println();
		return response;
	}
	
	public int getResponseNumber() {
		scanner = new Scanner(System.in);
		int response =  scanner.nextInt();
		System.out.println();
		return response;
	}

	@Override
	public String getFile() {
		return getResponse();
	}


	@Override
	public void sendMessage(List<FreeAgentModel> free) {
		System.out.printf("%1s %-12s %9s %31s %11s %13s %10s %10s %10s","","Number","Name","Position",
				"Age","Checking","Skating","Shooting","Saving");
		System.out.println();
		System.out.println("--------------------------------------------------------------"
				+ "-------------------------------------------------------");
		IntStream.range(0, free.size()).forEach(index->{
			System.out.printf("%2s %-15s %-17s %19s %11s %10s %11s %10s %10s",
					"",index+1,free.get(index).getPlayerName(),free.get(index).getPosition(),
					free.get(index).getAge(),free.get(index).getChecking(),
					"8.8","10",
					free.get(index).getSaving());
			System.out.println();
		});
		System.out.println("--------------------------------------------------------------"
				+ "-------------------------------------------------------");
		System.out.println("Please Enter an number to add player");
	}


	@Override
	public void sendCoachMessage(List<CoachModel> coachList) {
		System.out.printf("%1s %-12s %9s %31s %11s %13s %10s ","","Number","Name",
				"Checking","Skating","Shooting","Saving");
		System.out.println();
		System.out.println("--------------------------------------------------------------"
				+ "-------------------------------------------------------");
		IntStream.range(0, coachList.size()).forEach(index->{
			System.out.printf("%2s %-15s %-16s %19s %11s %11s %11s",
					"",index+1,coachList.get(index).getName(),coachList.get(index).getChecking(),
					coachList.get(index).getSkating(),coachList.get(index).getShooting(),
					coachList.get(index).getSaving());
			System.out.println();
		});
		System.out.println("--------------------------------------------------------------"
				+ "-------------------------------------------------------");
		System.out.println("Please Enter an number to Coach");
		
	}

}
