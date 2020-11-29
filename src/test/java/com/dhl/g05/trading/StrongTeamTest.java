package com.dhl.g05.trading;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.util.List;

import org.junit.Test;

import com.dhl.g05.model.ConferenceModel;
import com.dhl.g05.model.DivisionModel;
import com.dhl.g05.model.FreeAgentModel;
import com.dhl.g05.model.IConference;
import com.dhl.g05.model.IDivision;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.IPlayer;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.simulation.ITradingConfig;

public class StrongTeamTest {

	private static IStrongTeam strongTeam;
	private static IWeakTeam weakTeam;

	MockLeagueModel mockLeague = new MockLeagueModel();
	WeakTeamTest weakTest = new WeakTeamTest();
	ITeam strongestTeam = mockLeague.leagueMock4();

	@Test
	public void setStrongTeamTest() {
		strongTeam = TradeAbstractFactory.instance().getStrongteam();
		strongTeam.setStrongTeam(strongestTeam);
		assertEquals(strongestTeam, strongTeam.getStrongTeam());
	}

	@Test
	public void getWeakTeamTest() {
		strongTeam = TradeAbstractFactory.instance().getStrongteam();
		strongTeam.setStrongTeam(mockLeague.leagueMock4());
		assertNotSame(mockLeague.leagueMock4(), strongTeam.getStrongTeam());
	}

	@Test
	public void setConferenceNameTest() {
		IConference conference = new ConferenceModel();
		strongTeam = TradeAbstractFactory.instance().getStrongteam();
		conference.setConferenceName("Western");
		strongTeam.setConferenceName(conference.getConferenceName());
		assertSame(strongTeam.getConferenceName(), conference.getConferenceName());
	}

	@Test
	public void setDivisionNameTest() {
		IDivision division = new DivisionModel();
		strongTeam = TradeAbstractFactory.instance().getStrongteam();
		division.setDivisionName("Indian");
		strongTeam.setDivisionName(division.getDivisionName());
		assertSame(strongTeam.getDivisionName(), division.getDivisionName());
	}

	@Test
	public void getDivisionNameTest() {
		IDivision division = new DivisionModel();
		strongTeam = TradeAbstractFactory.instance().getStrongteam();
		division.setDivisionName("Pacific");
		strongTeam.setDivisionName(division.getDivisionName());
		assertSame(strongTeam.getDivisionName(), division.getDivisionName());
	}

	@Test
	public void setStrongestPlayersToTradeTest() {
		MockLeagueModel mockLeague = new MockLeagueModel();
		strongTeam = TradeAbstractFactory.instance().getStrongteam();
		strongTeam.setStrongestPlayersToTrade(mockLeague.leagueMock2());
		assertSame(strongTeam.getStrongestPlayersToTrade().size(), 2);
	}

	@Test
	public void getStrongestPlayersToTradeTest1() {
		MockLeagueModel mockLeague = new MockLeagueModel();
		strongTeam = TradeAbstractFactory.instance().getStrongteam();
		strongTeam.setStrongestPlayersToTrade(mockLeague.leagueMock3());
		assertSame(strongTeam.getStrongestPlayersToTrade().size(), 1);
	}

	@Test
	public void getStrengthOfStrongestPlayersTest1() {
		strongTeam = TradeAbstractFactory.instance().getStrongteam();
		strongTeam.setStrengthOfStrongestPlayers(5);
		assertNotSame(strongTeam.getStrengthOfStrongestPlayers(), 4);
	}

	@Test
	public void getStrengthOfStrongestPlayersTest2() {
		strongTeam = TradeAbstractFactory.instance().getStrongteam();
		strongTeam.setStrengthOfStrongestPlayers(5);
		assertEquals(strongTeam.getStrengthOfStrongestPlayers(), 5, 0);
	}

	@Test
	public void setStrengthOfStrongestPlayersTest() {
		strongTeam = TradeAbstractFactory.instance().getStrongteam();
		strongTeam.setStrengthOfStrongestPlayers(8);
		assertEquals(strongTeam.getStrengthOfStrongestPlayers(), 8, 0);
	}

	@Test
	public void findTeamToSwapTest() {
		ILeague league = mockLeague.leagueMock();
		ITradingConfig trade = mockLeague.TradingConfigMock();
		weakTeam = TradeAbstractFactory.instance().getWeakteam();
		for (IConference conference : league.getConferenceDetails()) {
			for (IDivision division : conference.getDivisionDetails()) {
				for (ITeam team : division.getTeamDetails()) {
					if (team.getTeamName().equals("Tigers")) {
						weakTeam.setConferenceName("Eastern");
					}
					weakTeam.setDivisionName("Atlantic");
					weakTeam.setOfferedPlayerPosition("goalie");
					weakTeam.setNumberOfPlayersOffered(1);
					weakTeam.setPlayersOffered(team.getPlayerList());
					weakTeam.setStrengthOfPlayersOffered(3);
					weakTeam.setWeakTeam(team);
					weakTeam.playersToOffer(trade);
					break;
				}
			}
		}
		strongTeam = TradeAbstractFactory.instance().getStrongteam();
		boolean result = strongTeam.findTeamToSwap(league);
		String expectedTeamName = strongTeam.getStrongTeam().getTeamName();
		List<IPlayer> position = strongTeam.getStrongestPlayersToTrade();
		assertEquals("Rythm", expectedTeamName);
		assertTrue(result);
		assertEquals("player1Team2", ((FreeAgentModel) position.get(0)).getPlayerName());
		assertEquals("defense", position.get(0).getPosition());
	}

}
