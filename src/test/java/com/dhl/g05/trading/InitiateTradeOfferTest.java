package com.dhl.g05.trading;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.IConference;
import com.dhl.g05.model.IDivision;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.IPlayer;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.simulation.ITradingConfig;

public class InitiateTradeOfferTest {

	private static IIntiateTradeOffer intiateTradeOffer;

	@Test
	public void teamPlayersSwappedAfterTradeAcceptedTest() {
		List<ITeam> teams = initiateTradeOfferTest();
		List<IPlayer> players = teams.get(0).getPlayerList();
		String playerGained = players.get(4).getPlayerName();
		String playerTraded = teams.get(1).getPlayerList().get(4).getPlayerName();
		assertEquals(playerGained, "player1Team2");
		assertEquals(playerTraded, "Player5Team1");
	}

	@Test
	public void LossPointAfterTradeAcceptedTest() {
		List<ITeam> teams = initiateTradeOfferTest();
		assertEquals(teams.get(0).getLossCount(), 0);
	}

	@Test
	public void LossPointAfterTradeRejectedTest() {
		List<ITeam> teams = initiateTradeOfferTest();
		assertEquals(teams.get(1).getLossCount(), 2);
	}

	public List<ITeam> initiateTradeOfferTest() {
		MockLeagueModel mock = new MockLeagueModel();
		ILeague league = mock.leagueMock();
		ITradingConfig trade = mock.tradingConfigMock();
		intiateTradeOffer = ApplicationConfiguration.instance().getTradingConcreteFactoryState().createInititatetradeoffer();
		intiateTradeOffer.setTrade(trade);
		ILeague newLeague = intiateTradeOffer.initiateTradeOffer(league);
		List<ITeam> teams = new ArrayList<>();
		for (IConference c : newLeague.getConferenceDetails()) {
			for (IDivision d : c.getDivisionDetails()) {
				for (ITeam t : d.getTeamDetails()) {
					{
						teams.add(t);
					}
				}
			}
		}
		return teams;
	}
}
