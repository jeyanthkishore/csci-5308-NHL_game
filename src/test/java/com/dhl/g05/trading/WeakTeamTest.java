package com.dhl.g05.trading;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.dhl.g05.leaguemodel.gameplayconfig.TradingModel;
import com.dhl.g05.leaguemodel.player.PlayerModel;
import com.dhl.g05.leaguemodel.team.TeamModel;

public class WeakTeamTest {
	
	@Test
     public void callWeakPlayersAndGetPositionTest()
     {
		MockTradingObject tradeMock = new MockTradingObject();
		WeakTeam weakTeam= new WeakTeam();
		TradingModel trade = tradeMock.TradingModelTest();
		TeamModel weak = mock1();
		List<PlayerModel> expected= weakTeam.callWeakPlayersAndGetPosition(weak, trade);
		List<PlayerModel> actual =mock2().getPlayerList();
		assertEquals(actual.size(),expected.size());
     }

	public  TeamModel mock1(){
		TeamModel weakTeam= new TeamModel();
		weakTeam.setTeamName("Tigers");
		
		PlayerModel player1 = new PlayerModel();
		player1.setPlayerName("Brian");
		player1.setPosition("defense");
		player1.setPlayerStrength(8);
		PlayerModel player2 = new PlayerModel();
		player2.setPlayerName("James");
		player2.setPlayerStrength(10);
		player2.setPosition("forward");
		PlayerModel player3 = new PlayerModel();
		player3.setPlayerName("Lily");
		player3.setPlayerStrength(6);
		player3.setPosition("goalie");
		PlayerModel player4 = new PlayerModel();
		player4.setPlayerName("Harry");
		player4.setPlayerStrength(4);
		player4.setPosition("forward");
		PlayerModel player5 = new PlayerModel();
		player5.setPlayerName("Shawn");
		player5.setPlayerStrength(3);
		player5.setPosition("defense");
		
		List<PlayerModel> playerDetails = new ArrayList<PlayerModel>();
		playerDetails.add(player1);
		playerDetails.add(player2);
		playerDetails.add(player3);
		playerDetails.add(player4);
		playerDetails.add(player5);
	    weakTeam.setPlayerList(playerDetails);
	    return weakTeam;
			}
	
	public  TeamModel mock2(){
		TeamModel weakTeam= new TeamModel();
		weakTeam.setTeamName("Tigers");
		
		PlayerModel player3 = new PlayerModel();
		player3.setPlayerName("Lily");
		player3.setPlayerStrength(6);
		player3.setPosition("goalie");
		PlayerModel player4 = new PlayerModel();
		player4.setPlayerName("Harry");
		player4.setPlayerStrength(4);
		player4.setPosition("forward");
		PlayerModel player5 = new PlayerModel();
		player5.setPlayerName("Shawn");
		player5.setPlayerStrength(3);
		player5.setPosition("defense");
		
		List<PlayerModel> playerDetails = new ArrayList<PlayerModel>();
		playerDetails.add(player5);
		playerDetails.add(player4);
		playerDetails.add(player3);
		weakTeam.setPlayerList(playerDetails);
		
		return weakTeam;
		
		
	}
		
	


}
