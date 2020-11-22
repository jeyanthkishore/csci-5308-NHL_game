package com.dhl.g05.trading;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.dhl.g05.player.PlayerModel;
import com.dhl.g05.team.TeamModel;

public class ResolveTradeTest {
	private static final String FORWARD = "Forward";
	private static final String DEFENSE = "Defense";
	
	@Test
	public void dropWeakestPlayersToFreeAgentListTest() {
        ResolveTrade resolve = new ResolveTrade();
		PlayerModel player1 = new PlayerModel();
		player1.setPlayerName("Shawn");
		player1.setPosition(DEFENSE);
		player1.setPlayerStrength(10);
		player1.setChecking(5);
		player1.setShooting(5);
		player1.setSkating(5);
		PlayerModel player2 = new PlayerModel();
		player2.setPlayerName("Mendes");
		player2.setPosition(FORWARD);
		player2.setPlayerStrength(5);
		player2.setChecking(2);
		player2.setShooting(2);
		player2.setSkating(2);
		List<PlayerModel> players = new ArrayList<PlayerModel>();
		players.add(player1);
		players.add(player2);
		TeamModel team= new  TeamModel();
        team.setPlayerList(players);
		resolve.dropToFreeAgentList(team, DEFENSE, 1);
        assertEquals(team.getPlayerList().size(),2);
	}
	

}
