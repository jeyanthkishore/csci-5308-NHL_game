package com.dhl.g05.statemachine.mocks;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import com.dhl.g05.leaguemodel.FreeAgentObject;
import com.dhl.g05.statemachine.IPlayerCommunication;

public class MockPlayerCommunication implements IPlayerCommunication{

	@Override
	public void sendMessage(String message) {
		System.out.println(message);
		System.out.println();
	}

	@Override
	public String getResponse() {
		return "mock response";
	}
	
	@Override
	public String getFile() {
		return "src/test/java/com/dhl/g05/jsontestfiles/jsonGoodInfo.json";
	}

	@Override
	public void sendMessage(List<FreeAgentObject> free) {
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
	public int getResponseNumber() {
		Random rand = new Random();
		int number =  rand.nextInt(4);
		if (number==0){          
			number= number+1;
		}
		return number;
	}
}
