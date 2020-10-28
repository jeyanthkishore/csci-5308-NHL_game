package com.dhl.g05.trading;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dhl.g05.leaguemodel.gameplayconfig.TradingModel;

public class CheckTradeValueTest {

	@Test
	public void checkTradeValue() {
		MockTradingObject tradeMock = new MockTradingObject();
		TradingModel trade = tradeMock.TradingModelTest();

		CheckTradeValue tradeValue = new CheckTradeValue(trade);
		assertTrue(tradeValue.checkTradeValue());		
	}
}
	