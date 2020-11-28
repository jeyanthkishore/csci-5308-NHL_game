package com.dhl.g05.trading;

import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.gameplayconfig.ITradingConfig;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.team.ITeam;

public class InitiateTradeOfferTest {

	private static IIntiateTradeOffer intiateTradeOffer;

	@Test
	public void TeamStrengthAfterTradeTest() {
		ILeague league = initiateTradeOfferTest();
		for (IConference c : league.getConferenceDetails()) {
			for (IDivision d : c.getDivisionDetails()) {
				for (ITeam t : d.getTeamDetails()) {
					{
						assertSame(t.getPlayerList().size(), 5);
					}
				}
			}
		}
	}

	public ILeague initiateTradeOfferTest() {
		MockLeagueModel mock = new MockLeagueModel();
		ILeague league = mock.leagueMock();
		ITradingConfig trade = mock.TradingConfigMock();
		intiateTradeOffer = AbstractTradingFactory.instance().getInititatetradeoffer();
		intiateTradeOffer.setTrade(trade);
		return intiateTradeOffer.initiateTradeOffer(league);
	}

}
