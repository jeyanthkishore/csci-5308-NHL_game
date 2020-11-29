package com.dhl.g05.trading;

import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.IConference;
import com.dhl.g05.model.IDivision;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.simulation.ITradingConfig;

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
		intiateTradeOffer = ApplicationConfiguration.instance().getTradingConcreteFactoryState().createInititatetradeoffer();
		intiateTradeOffer.setTrade(trade);
		return intiateTradeOffer.initiateTradeOffer(league);
	}

}
