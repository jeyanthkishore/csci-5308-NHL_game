package com.dhl.g05.trading;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import com.dhl.g05.gameplayconfig.TradingModel;
import com.dhl.g05.league.LeagueModel;


public class InitiateTradeOfferTest {

	@Test
	public void generateTradeOfferTest() {
		MockLeagueModel mockLeague = new MockLeagueModel();
		LeagueModel league = mockLeague.leagueMock1();
		MockTradeConfig tradeMock = new MockTradeConfig();
		TradingModel trade = tradeMock.TradingModelTest();
		IIntiateTradeOffer startTrade = new InitiateTradeOffer();
		startTrade.setTrade(trade);
		startTrade.initiateTradeOffer(league);
	    assertNotNull(league);
	    assertNotNull(startTrade.getTrade());

	}

}
