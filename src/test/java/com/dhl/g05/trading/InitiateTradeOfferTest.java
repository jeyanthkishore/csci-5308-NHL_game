package com.dhl.g05.trading;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;

import com.dhl.g05.gameplayconfig.TradingModel;
import com.dhl.g05.league.LeagueModel;

public class InitiateTradeOfferTest {
	MockLeagueModel mockLeague = new MockLeagueModel();
	LeagueModel league = mockLeague.leagueMock1();
	TradingModel trade = mockLeague.TradingConfigMock();
	IIntiateTradeOffer startTrade = new InitiateTradeOffer();

	@Test
	public void generateTradeOfferTest1() {
		startTrade.setTrade(trade);
		startTrade.initiateTradeOffer(league);
	    assertNotNull(league);
	}
	
	@Test
	public void generateTradeOfferTest2() {
		startTrade.setTrade(trade);
		startTrade.initiateTradeOffer(league);
	    assertNotNull(startTrade.getTrade());
	}
}
