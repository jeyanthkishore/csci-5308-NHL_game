package com.dhl.g05.communication;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

import com.dhl.g05.model.FreeAgentModel;
import com.dhl.g05.model.ICoach;
import com.dhl.g05.model.IFreeAgent;
import com.dhl.g05.model.IPlayer;

public class PlayerCommunication implements IPlayerCommunication, ITradeCommunication {
	
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

	public int getResponseNumber() {
		scanner = new Scanner(System.in);
		int response = scanner.nextInt();
		System.out.println();
		return response;
	}

	@Override
	public String getFile() {
		return getResponse();
	}

	@Override
	public void sendMessage(List<IFreeAgent> free) {
		Map<Integer, Integer> columnLength = new HashMap<Integer, Integer>();
		StringBuilder format = new StringBuilder();
		String header[] = { "Number", "Name", "Position", "Age", "Checking", "Skating", "Shooting", "Saving" };

		IntStream.range(0, header.length).forEach(a -> {
			if (columnLength.get(a) == null) {
				columnLength.put(a, header[a].length());
			}
		});
		IntStream.range(0, free.size()).forEach(a -> {
			String name = free.get(a).getPlayerName();
			if (columnLength.get(1) < name.length()) {
				columnLength.put(1, name.length());
			}
		});
		columnLength.entrySet().stream().forEach(e -> format.append("| %" + "" + e.getValue() + "s "));
		format.append("|\n");
		System.out.printf(format.toString(), header[0], header[1], header[2], header[3], header[4], header[5],
				header[6], header[7]);
		System.out.println(
				"--------------------------------------------------------------" + "--------------------------------");
		IntStream.range(0, free.size()).forEach(index -> {
			System.out.printf(format.toString(), index + 1, free.get(index).getPlayerName(),
					free.get(index).getPosition(), free.get(index).getAge(), free.get(index).getChecking(),
					free.get(index).getSkating(), free.get(index).getShooting(), free.get(index).getSaving());
		});
		System.out.println(
				"--------------------------------------------------------------" + "--------------------------------");

	}

	@Override
	public void sendCoachMessage(List<ICoach> coachList) {
		Map<Integer, Integer> columnLength = new HashMap<Integer, Integer>();
		StringBuilder format = new StringBuilder();
		String header[] = { "Number", "Name", "Checking", "Skating", "Shooting", "Saving" };
		IntStream.range(0, header.length).forEach(a -> {
			if (columnLength.get(a) == null) {
				columnLength.put(a, header[a].length());
			}
		});
		IntStream.range(0, coachList.size()).forEach(a -> {
			String name = coachList.get(a).getName();
			if (columnLength.get(1) < name.length()) {
				columnLength.put(1, name.length());
			}
		});
		columnLength.entrySet().stream().forEach(e -> format.append("| %" + "" + e.getValue() + "s "));
		format.append("|\n");
		System.out.printf(format.toString(), header[0], header[1], header[2], header[3], header[4], header[5]);
		System.out.println("--------------------------------------------------------------" + "------------");
		IntStream.range(0, coachList.size()).forEach(index -> {
			System.out.printf(format.toString(), index + 1, coachList.get(index).getName(),
					coachList.get(index).getChecking(), coachList.get(index).getSkating(),
					coachList.get(index).getShooting(), coachList.get(index).getSaving());
		});
		System.out.println("--------------------------------------------------------------" + "------------");

	}

	@Override
	public void sendManagerMessage(List<String> managerList) {
		Map<Integer, Integer> columnLength = new HashMap<Integer, Integer>();
		StringBuilder format = new StringBuilder();
		String header[] = { "Number", "Name" };
		IntStream.range(0, header.length).forEach(a -> {
			if (columnLength.get(a) == null) {
				columnLength.put(a, header[a].length());
			}
		});
		IntStream.range(0, managerList.size()).forEach(a -> {
			String name = managerList.get(a);
			if (columnLength.get(1) < name.length()) {
				columnLength.put(1, name.length());
			}
		});
		columnLength.entrySet().stream().forEach(e -> format.append("| %" + "" + e.getValue() + "s "));
		format.append("|\n");
		System.out.printf(format.toString(), header[0], header[1]);
		System.out.println("--------------------------------------------------------------");
		IntStream.range(0, managerList.size()).forEach(index -> {
			System.out.printf(format.toString(), index + 1, managerList.get(index));
		});
		System.out.println("--------------------------------------------------------------");
	}

	@Override
	public void sendTradeMessage(List<IPlayer> playersOffered, List<IPlayer> playersRequested) {
		System.out.println("Players offered and their statistics are :");
		for (IPlayer player : playersOffered) {
			System.out.println("Name -  " + ((FreeAgentModel) player).getPlayerName() + " position -  "
					+ player.getPosition() + " Strength -  " + player.getPlayerStrength());
		}
		System.out.println();

		System.out.println("Playres requested from your team are: ");
		for (IPlayer player : playersRequested) {
			System.out.println("Name -  " + ((FreeAgentModel) player).getPlayerName() + " position -  "
					+ player.getPosition() + " Strength -  " + player.getPlayerStrength());
		}
		System.out.println();
	}

	@Override
	public int getTradeDecision() {
		int response;
		try {
			System.out.println("Enter 1 to accept and 2 to reject");
			return getResponseNumber();
		} catch (InputMismatchException e) {
			System.out.println("OOPS input mismatch, only number allowed!");
			response = getTradeDecision();

		}
		return response;
	}

	@Override
	public void DropPlayerDetails(List<IPlayer> playersToDrop, int count) {

		System.out.println("Players to drop are :" + count);
		for (IPlayer player : playersToDrop) {
			System.out.println("Name -  " + ((FreeAgentModel) player).getPlayerName() + " position -  "
					+ player.getPosition() + " Strength -  " + player.getPlayerStrength());
		}
		System.out.println();

	}

	@Override
	public void AddPlayerDetails(List<IFreeAgent> freeAgents, int count) {
		System.out.println("Players to add are :" + count);
		for (IFreeAgent player : freeAgents) {
			System.out.println("Name -  " + player.getPlayerName() + " position -  " + player.getPosition()
					+ " Strength -  " + player.getPlayerStrength());
		}
		System.out.println();
	}

}
