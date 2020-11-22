package com.dhl.g05.trading;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import java.util.List;

import org.junit.Test;

import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.freeagent.FreeAgentModel;
import com.dhl.g05.gameplayconfig.ITradingConfig;
import com.dhl.g05.player.IPlayer;
import com.dhl.g05.team.ITeam;

public class WeakTeamTest {
	MockLeagueModel mockLeague = new MockLeagueModel();
	IWeakTeam weakTeam = Trading.instance().getWeakteam();
	ITradingConfig trade = mockLeague.TradingConfigMock();
	ITeam weak = mockLeague.leagueMock4();

	@Test
	public void setWeakTeamTest() {
		IWeakTeam weakTeam = Trading.instance().getWeakteam();
		weakTeam.setWeakTeam(weak);
		assertSame(weak, weakTeam.getWeakTeam());
	}

	@Test
	public void getWeakTeamTest() {
		IWeakTeam weakTeam = new WeakTeam();
		assertNotSame(weak, weakTeam.getWeakTeam());
	}

	@Test
	public void setConferenceNameTest() {
		IConference conference = new ConferenceModel();
		WeakTeam weakTeam = new WeakTeam();
		conference.setConferenceName("Eastern");
		weakTeam.setConferenceName(conference.getConferenceName());
		assertSame(weakTeam.getConferenceName(), conference.getConferenceName());
	}

	@Test
	public void setDivisionNameTest() {
		IDivision division = new DivisionModel();
		IWeakTeam weakTeam = Trading.instance().getWeakteam();
		division.setDivisionName("Atlantic");
		weakTeam.setDivisionName(division.getDivisionName());
		assertSame(weakTeam.getDivisionName(), division.getDivisionName());
	}

	@Test
	public void getDivisionNameTest() {
		IDivision division = new DivisionModel();
		IWeakTeam weakTeam = Trading.instance().getWeakteam();
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
		List<IPlayer> expected = weakTeam.getPlayersOffered();
		assertEquals(1, expected.size());

	}

	@Test
	public void setPlayersOfferedTest2() {
		weakTeam.setWeakTeam(weak);
		weakTeam.playersToOffer(trade);
		List<IPlayer> expected = weakTeam.getPlayersOffered();
		assertEquals("Shawn", ((FreeAgentModel) expected.get(0)).getPlayerName());
	}

	@Test
	public void setOfferedPlayerPositionTest() {
		weakTeam.setWeakTeam(weak);
		weakTeam.playersToOffer(trade);
		List<IPlayer> expected = weakTeam.getPlayersOffered();
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
		for (IPlayer player : weakTeam.getPlayersOffered()) {
			assertEquals("goalie", player.getPosition());
		}
	}
}
