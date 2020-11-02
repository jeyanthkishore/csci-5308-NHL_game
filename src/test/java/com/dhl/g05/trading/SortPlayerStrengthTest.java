package com.dhl.g05.trading;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.dhl.g05.player.PlayerModel;

public class SortPlayerStrengthTest {

	@Test
	public void sortByAscendingTest() {
		SortPlayerStrength sort = new SortPlayerStrength();
		PlayerModel player1 = new PlayerModel();
		player1.setPlayerName("Brian");
		player1.setPlayerStrength(8);
		PlayerModel player2 = new PlayerModel();
		player2.setPlayerName("James");
		player2.setPlayerStrength(10);
		PlayerModel player3 = new PlayerModel();
		player3.setPlayerName("Lily");
		player3.setPlayerStrength(6);

		List<PlayerModel> playerDetails = new ArrayList<PlayerModel>();
		playerDetails.add(player1);
		playerDetails.add(player2);
		playerDetails.add(player3);
		List<PlayerModel> expectedAscending = sort.sortByAscending(playerDetails);

		List<PlayerModel> actualAsc = new ArrayList<PlayerModel>();
		actualAsc.add(player3);
		actualAsc.add(player1);
		actualAsc.add(player2);
		assertTrue(expectedAscending.equals(actualAsc));
	}

	@Test
	public void sortByDescendingTest() {
		SortPlayerStrength sort = new SortPlayerStrength();
		PlayerModel player1 = new PlayerModel();
		player1.setPlayerName("Brian");
		player1.setPlayerStrength(8);
		PlayerModel player2 = new PlayerModel();
		player2.setPlayerName("James");
		player2.setPlayerStrength(10);
		PlayerModel player3 = new PlayerModel();
		player3.setPlayerName("Lily");
		player3.setPlayerStrength(6);
		List<PlayerModel> playerDetails = new ArrayList<PlayerModel>();
		playerDetails.add(player1);
		playerDetails.add(player2);
		playerDetails.add(player3);
		List<PlayerModel> expectedDescending = sort.sortByDescending(playerDetails);
		List<PlayerModel> actualDesc = new ArrayList<PlayerModel>();
		actualDesc.add(player2);
		actualDesc.add(player1);
		actualDesc.add(player3);
		assertTrue(expectedDescending.equals(actualDesc));
	}

}
