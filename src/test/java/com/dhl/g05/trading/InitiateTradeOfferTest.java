package com.dhl.g05.trading;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dhl.g05.gamePlayConfig.TradingModel;
import com.dhl.g05.leaguemodel.LeagueObject;

public class InitiateTradeOfferTest{

	@Test
	public void generateTradeOfferTest() {

		
		LeagueModelTest leagueMock= new LeagueModelTest();
		LeagueObject league= leagueMock.leagueMock();
		
		MockTradingObject tradeMock= new MockTradingObject();
		InitiateTradeOffer trade= new InitiateTradeOffer();
		TradingModel gameTrade = tradeMock.TradingModelTest();
		
		assertTrue(trade.generateTradeOffer(league,gameTrade));

	}

	

}
