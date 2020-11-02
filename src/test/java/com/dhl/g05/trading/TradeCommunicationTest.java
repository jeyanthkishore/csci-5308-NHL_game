package com.dhl.g05.trading;

import java.util.ArrayList;
import java.util.List;

import com.dhl.g05.player.PlayerModel;


public class TradeCommunicationTest {
	
	public void sendTradeMessage1()
	{
		//ITradeCommunication com= new PlayerCommunication();
		
	}
	
	public List<PlayerModel> mock1()
	{
		List<PlayerModel> playersOffered = new ArrayList<PlayerModel>();
		PlayerModel player1= new PlayerModel();
		player1.setPlayerName("Brian");
		player1.setPlayerStrength(4);
		player1.setPosition("goalie");
		playersOffered.add(player1);
		return playersOffered;		
	}
	
	public List<PlayerModel> mock2()
	{
		List<PlayerModel> playersRequested = new ArrayList<PlayerModel>();
		PlayerModel player1= new PlayerModel();
		player1.setPlayerName("Shawn");
		player1.setPlayerStrength(8);
		player1.setPosition("goalie");
		playersRequested.add(player1);
		return playersRequested;	
	}
	

}
