package com.dhl.g05.communication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
		Map<Integer,Integer> columnLength = new HashMap<Integer,Integer>();
		StringBuilder format = new StringBuilder();
		String header [] = {"Number","Name","Position",
				"Age","Checking","Skating","Shooting","Saving"};
		
		IntStream.range(0, header.length).forEach(a->{
			if(columnLength.get(a)==null) {
				columnLength.put(a, header[a].length());
			}
		});
		IntStream.range(0, free.size()).forEach(a->{
			String name = free.get(a).getPlayerName();
			if(columnLength.get(1) < name.length()) {
				columnLength.put(1, name.length());
			}
		});
		columnLength.entrySet().stream().forEach(e -> format.append("| %" + "" + e.getValue() + "s "));
		format.append("|\n");
		System.out.printf(format.toString(),header[0],header[1],header[2],header[3],header[4],header[5]
				,header[6],header[7]);
		System.out.println("--------------------------------------------------------------"
				+ "--------------------------------");
		IntStream.range(0, free.size()).forEach(index->{
			System.out.printf(format.toString(),
					index+1,free.get(index).getPlayerName(),free.get(index).getPosition(),
					free.get(index).getAge(),free.get(index).getChecking(),
					free.get(index).getSkating(),free.get(index).getShooting(),
					free.get(index).getSaving());
		});
		System.out.println("--------------------------------------------------------------"
				+ "--------------------------------");
		
	}


	@Override
	public void sendCoachMessage(List<CoachModel> coachList) {
		Map<Integer,Integer> columnLength = new HashMap<Integer,Integer>();
		StringBuilder format = new StringBuilder();
		String header [] = {"Number","Name","Checking","Skating","Shooting","Saving"};
		IntStream.range(0, header.length).forEach(a->{
			if(columnLength.get(a)==null) {
				columnLength.put(a, header[a].length());
			}
		});
		IntStream.range(0, coachList.size()).forEach(a->{
			String name = coachList.get(a).getName();
			if(columnLength.get(1) < name.length()) {
				columnLength.put(1, name.length());
			}
		});
		columnLength.entrySet().stream().forEach(e -> format.append("| %" + "" + e.getValue() + "s "));
		format.append("|\n");
		System.out.printf(format.toString(),header[0],header[1],header[2],header[3],header[4],header[5]);
		System.out.println("--------------------------------------------------------------"
				+ "------------");
		IntStream.range(0, coachList.size()).forEach(index->{
			System.out.printf(format.toString(),index+1,coachList.get(index).getName(),coachList.get(index).getChecking(),
					coachList.get(index).getSkating(),coachList.get(index).getShooting(),
					coachList.get(index).getSaving());
		});
		System.out.println("--------------------------------------------------------------"
				+ "------------");

	}


	@Override
	public void sendManagerMessage(List<String> managerList) {
		Map<Integer,Integer> columnLength = new HashMap<Integer,Integer>();
		StringBuilder format = new StringBuilder();
		String header [] = {"Number","Name"};
		IntStream.range(0, header.length).forEach(a->{
			if(columnLength.get(a)==null) {
				columnLength.put(a, header[a].length());
			}
		});
		IntStream.range(0, managerList.size()).forEach(a->{
			String name = managerList.get(a);
			if(columnLength.get(1) < name.length()) {
				columnLength.put(1, name.length());
			}
		});
		columnLength.entrySet().stream().forEach(e -> format.append("| %" + "" + e.getValue() + "s "));
		format.append("|\n");
		System.out.printf(format.toString(),header[0],header[1]);
		System.out.println("--------------------------------------------------------------");
		IntStream.range(0, managerList.size()).forEach(index->{
			System.out.printf(format.toString(),index+1,managerList.get(index));
		});
		System.out.println("--------------------------------------------------------------");
	}

}
