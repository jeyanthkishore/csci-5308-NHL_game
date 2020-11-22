package com.dhl.g05.trading;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import java.util.List;

import org.junit.Test;

import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.gameplayconfig.TradingModel;
import com.dhl.g05.player.PlayerModel;
import com.dhl.g05.team.TeamModel;

public class WeakTeamTest {
	MockLeagueModel mockLeague = new MockLeagueModel();
	WeakTeam weakTeam = new WeakTeam();
	TradingModel trade =mockLeague.TradingConfigMock();
	TeamModel weak = mockLeague.leagueMock4();

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
		weakTeam.setWeakTeam(mockLeague.leagueMock5());
		weakTeam.playersToOffer(trade);
		assertEquals(2, weakTeam.getNumberOfPlayersOffered());
	}

	@Test
	public void playersToOfferTest2() {
		weakTeam.setWeakTeam(mockLeague.leagueMock5());
		weakTeam.playersToOffer(trade);
		for (PlayerModel player : weakTeam.getPlayersOffered()) {
			assertEquals("goalie", player.getPosition());
		}
	}
}
