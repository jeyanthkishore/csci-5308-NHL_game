package com.dhl.g05.trading;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.gameplayconfig.TradingModel;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.player.PlayerModel;
import com.dhl.g05.team.TeamModel;

public class StrongTeamTest {

	WeakTeamTest weakTest = new WeakTeamTest();
	WeakTeam weakTeam = new WeakTeam();
	TeamModel strongestTeam = weakTest.mock1();

	@Test
	public void setStrongTeamTest() {
		StrongTeam strong = new StrongTeam();
		strong.setStrongTeam(strongestTeam);
		assertEquals(strongestTeam, strong.getStrongTeam());
	}

	@Test
	public void getWeakTeamTest() {
		StrongTeam strong = new StrongTeam();
		strong.setStrongTeam(weakTest.mock2());
		assertNotSame(weakTest.mock1(), strong.getStrongTeam());
	}

	@Test
	public void setConferenceNameTest() {
		ConferenceModel conference = new ConferenceModel();
		StrongTeam strong = new StrongTeam();
		conference.setConferenceName("Western");
		strong.setConferenceName(conference.getConferenceName());
		assertSame(strong.getConferenceName(), conference.getConferenceName());
	}

	@Test
	public void setDivisionNameTest() {
		DivisionModel division = new DivisionModel();
		StrongTeam strong = new StrongTeam();
		division.setDivisionName("Indian");
		strong.setDivisionName(division.getDivisionName());
		assertSame(strong.getDivisionName(), division.getDivisionName());
	}

	@Test
	public void getDivisionNameTest() {
		DivisionModel division = new DivisionModel();
		StrongTeam strong = new StrongTeam();
		division.setDivisionName("Pacific");
		strong.setDivisionName(division.getDivisionName());
		assertSame(strong.getDivisionName(), division.getDivisionName());
	}

	@Test
	public void findTeamToSwapTest() {
		MockTradeConfig tradeMock = new MockTradeConfig();
		StrongTeam strong = new StrongTeam();
		MockLeagueModel mockLeague = new MockLeagueModel();
		LeagueModel league = mockLeague.leagueMock();
		TradingModel trade = tradeMock.TradingModelTest();
		IWeakTeam teamInitiatingTrade = TradingConfig.instance().getWeakteam();

		for (ConferenceModel conference : league.getConferenceDetails()) {
			for (DivisionModel division : conference.getDivisionDetails()) {
				for (TeamModel team : division.getTeamDetails()) {
					if (team.getTeamName().equals("Tigers"))
						teamInitiatingTrade.setConferenceName("Eastern");
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
		List<PlayerModel> position = strong.getStrongestPlayersToTrade();
		assertEquals("Rythm", expectedTeamName);
		assertTrue(result);
		assertEquals("player1Team2", position.get(0).getPlayerName());
		assertEquals("defense", position.get(0).getPosition());

	}

}
