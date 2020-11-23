package com.dhl.g05.trading;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.freeagent.FreeAgentModel;
import com.dhl.g05.gameplayconfig.ITradingConfig;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.player.IPlayer;
import com.dhl.g05.team.ITeam;

public class StrongTeamTest {

	MockLeagueModel mockLeague = new MockLeagueModel();
	WeakTeamTest weakTest = new WeakTeamTest();
	IWeakTeam weakTeam = Trading.instance().getWeakteam();
	ITeam strongestTeam = mockLeague.leagueMock4();

	@Test
	public void setStrongTeamTest() {
		IStrongTeam strong =Trading.instance().getStrongteam();
		strong.setStrongTeam(strongestTeam);
		assertEquals(strongestTeam, strong.getStrongTeam());
	}

	@Test
	public void getWeakTeamTest() {
		IStrongTeam strong = new StrongTeam();
		strong.setStrongTeam(mockLeague.leagueMock4());
		assertNotSame(mockLeague.leagueMock4(), strong.getStrongTeam());
	}

	@Test
	public void setConferenceNameTest() {
		IConference conference = new ConferenceModel();
		IStrongTeam strong = Trading.instance().getStrongteam();
		conference.setConferenceName("Western");
		strong.setConferenceName(conference.getConferenceName());
		assertSame(strong.getConferenceName(), conference.getConferenceName());
	}

	@Test
	public void setDivisionNameTest() {
		IDivision division = new DivisionModel();
		IStrongTeam strong = Trading.instance().getStrongteam();
		division.setDivisionName("Indian");
		strong.setDivisionName(division.getDivisionName());
		assertSame(strong.getDivisionName(), division.getDivisionName());
	}

	@Test
	public void getDivisionNameTest() {
		IDivision division = new DivisionModel();
		IStrongTeam strong = Trading.instance().getStrongteam();
		division.setDivisionName("Pacific");
		strong.setDivisionName(division.getDivisionName());
		assertSame(strong.getDivisionName(), division.getDivisionName());
	}

	@Test
	public void setStrongestPlayersToTradeTest() {
		MockLeagueModel mockLeague = new MockLeagueModel();
		IStrongTeam strong =Trading.instance().getStrongteam();
		strong.setStrongestPlayersToTrade(mockLeague.leagueMock2());
		assertSame(strong.getStrongestPlayersToTrade().size(), 2);
	}

	@Test
	public void getStrongestPlayersToTradeTest1() {
		MockLeagueModel mockLeague = new MockLeagueModel();
		IStrongTeam strong = Trading.instance().getStrongteam();
		strong.setStrongestPlayersToTrade(mockLeague.leagueMock3());
		assertSame(strong.getStrongestPlayersToTrade().size(), 1);
	}

	@Test
	public void getStrengthOfStrongestPlayersTest1() {
		IStrongTeam strong = Trading.instance().getStrongteam();
		strong.setStrengthOfStrongestPlayers(5);
		assertNotSame(strong.getStrengthOfStrongestPlayers(), 4);
	}

	@Test
	public void getStrengthOfStrongestPlayersTest2() {
		IStrongTeam strong = Trading.instance().getStrongteam();
		strong.setStrengthOfStrongestPlayers(5);
		assertEquals(strong.getStrengthOfStrongestPlayers(), 5, 0);
	}

	@Test
	public void setStrengthOfStrongestPlayersTest() {
		IStrongTeam strong = Trading.instance().getStrongteam();
		strong.setStrengthOfStrongestPlayers(8);
		assertEquals(strong.getStrengthOfStrongestPlayers(), 8, 0);
	}

	@Test
	public void findTeamToSwapTest() {
		IStrongTeam strong = Trading.instance().getStrongteam();
		ILeague league = mockLeague.leagueMock();
		ITradingConfig trade = mockLeague.TradingConfigMock();
		IWeakTeam teamInitiatingTrade = Trading.instance().getWeakteam();

		for (IConference conference : league.getConferenceDetails()) {
			for (IDivision division : conference.getDivisionDetails()) {
				for (ITeam team : division.getTeamDetails()) {
					if (team.getTeamName().equals("Tigers")) {
						teamInitiatingTrade.setConferenceName("Eastern");
					}
					teamInitiatingTrade.setDivisionName("Atlantic");
					teamInitiatingTrade.setOfferedPlayerPosition("goalie");
					teamInitiatingTrade.setNumberOfPlayersOffered(1);
					teamInitiatingTrade.setPlayersOffered(team.getPlayerList());
					teamInitiatingTrade.setStrengthOfPlayersOffered(3);
					teamInitiatingTrade.setWeakTeam(team);
					teamInitiatingTrade.playersToOffer(trade);
					break;
				}
			}
		}
		boolean result = strong.findTeamToSwap(league);
		String expectedTeamName = strong.getStrongTeam().getTeamName();
		List<IPlayer> position = strong.getStrongestPlayersToTrade();
		assertEquals("Rythm", expectedTeamName);
		assertTrue(result);
		assertEquals("player1Team2", ((FreeAgentModel) position.get(0)).getPlayerName());
		assertEquals("defense", position.get(0).getPosition());
	}

}
