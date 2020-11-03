package com.dhl.g05.trading;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.gameplayconfig.TradingModel;
import com.dhl.g05.player.PlayerModel;
import com.dhl.g05.team.TeamModel;

public class WeakTeamTest {

	MockTradeConfig tradeMock = new MockTradeConfig();
	WeakTeam weakTeam = new WeakTeam();
	TradingModel trade = tradeMock.TradingModelTest();
	TeamModel weak = mock1();

	@Test
	public void setWeakTeamTest() {
		WeakTeam weakTeam = new WeakTeam();
		
		weakTeam.setWeakTeam(weak);
		assertSame(weak, weakTeam.getWeakTeam());
	}

	@Test
	public void getWeakTeamTest() {
		WeakTeam weakTeam = new WeakTeam();
		assertNotSame(weak, weakTeam.getWeakTeam());
	}

	@Test
	public void setConferenceNameTest() {
		ConferenceModel conference = new ConferenceModel();
		WeakTeam weakTeam = new WeakTeam();
		conference.setConferenceName("Eastern");
		weakTeam.setConferenceName(conference.getConferenceName());
		assertSame(weakTeam.getConferenceName(), conference.getConferenceName());
	}

	@Test
	public void setDivisionNameTest() {
		DivisionModel division = new DivisionModel();
		WeakTeam weakTeam = new WeakTeam();
		division.setDivisionName("Atlantic");
		weakTeam.setDivisionName(division.getDivisionName());
		assertSame(weakTeam.getDivisionName(), division.getDivisionName());
	}

	@Test
	public void getDivisionNameTest() {
		DivisionModel division = new DivisionModel();
		WeakTeam weakTeam = new WeakTeam();
		division.setDivisionName("Pacific");
		weakTeam.setDivisionName(division.getDivisionName());
		assertSame(weakTeam.getDivisionName(), division.getDivisionName());
	}

	@Test
	public void setStrengthOfPlayersOfferedTest() {
		weakTeam.setWeakTeam(weak);
		weakTeam.playersToOffer(trade);
		double expected = weakTeam.getStrengthOfPlayersOffered();
		assertEquals(expected, 3.0, 0);

	}

	@Test
	public void setNumberOfPlayersOfferedTest() {
		weakTeam.setWeakTeam(weak);
		weakTeam.playersToOffer(trade);
		int expected = weakTeam.getNumberOfPlayersOffered();
		assertEquals(expected, 1);

	}

	@Test
	public void setPlayersOfferedTest1() {
		weakTeam.setWeakTeam(weak);
		weakTeam.playersToOffer(trade);
		List<PlayerModel> expected = weakTeam.getPlayersOffered();
		assertEquals(1, expected.size());

	}

	@Test
	public void setPlayersOfferedTest2() {
		weakTeam.setWeakTeam(weak);
		weakTeam.playersToOffer(trade);
		List<PlayerModel> expected = weakTeam.getPlayersOffered();
		assertEquals("Shawn", expected.get(0).getPlayerName());
	}

	@Test
	public void setOfferedPlayerPositionTest() {
		weakTeam.setWeakTeam(weak);
		weakTeam.playersToOffer(trade);
		List<PlayerModel> expected = weakTeam.getPlayersOffered();
		assertEquals("defense", expected.get(0).getPosition());
	}

	@Test
	public void playersToOfferTest1() {
		weakTeam.setWeakTeam(mock2());
		weakTeam.playersToOffer(trade);
		assertEquals(2, weakTeam.getNumberOfPlayersOffered());
	}

	@Test
	public void playersToOfferTest2() {
		weakTeam.setWeakTeam(mock2());
		weakTeam.playersToOffer(trade);
		for (PlayerModel player : weakTeam.getPlayersOffered()) {
			assertEquals("goalie", player.getPosition());
		}
	}

	public TeamModel mock1() {
		TeamModel weakTeam = new TeamModel();
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

	public TeamModel mock2() {
		TeamModel weakTeam = new TeamModel();
		weakTeam.setTeamName("TeamA");
		PlayerModel player1 = new PlayerModel();
		player1.setPlayerName("Player1");
		player1.setPosition("goalie");
		player1.setPlayerStrength(7);
		PlayerModel player2 = new PlayerModel();
		player2.setPlayerName("Player2");
		player2.setPosition("forward");
		player2.setPlayerStrength(10);
		PlayerModel player3 = new PlayerModel();
		player3.setPlayerName("Player3");
		player3.setPosition("goalie");
		player3.setPlayerStrength(5);
		PlayerModel player4 = new PlayerModel();
		player4.setPlayerName("Player4");
		player4.setPosition("defense");
		player4.setPlayerStrength(7);
		List<PlayerModel> playerDetails = new ArrayList<PlayerModel>();
		playerDetails.add(player1);
		playerDetails.add(player2);
		playerDetails.add(player3);
		playerDetails.add(player4);
		weakTeam.setPlayerList(playerDetails);
		return weakTeam;
	}

}
