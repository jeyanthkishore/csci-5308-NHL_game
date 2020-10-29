package com.dhl.g05.trading;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dhl.g05.leaguemodel.gameplayconfig.TradingModel;

public class CheckTradeValueTest {

	MockTradingObject tradeMock = new MockTradingObject();
	TradingModel trade = tradeMock.TradingModelTest();

	@Test
	public void checkGenerateRandomValue() {

		CheckTradeValue tradeValue = new CheckTradeValue(trade);
		double randomValue = tradeValue.generateRandomValue();
		assertTrue(randomValue >= 0.00 && randomValue <= 1.00);
	}

	@Test
	public void checkTradeValue() {

		CheckTradeValue tradeValue = new CheckTradeValue(trade);
		double randomValue = tradeValue.generateRandomValue();
		if (randomValue <= 1.0) {
			assertTrue(tradeValue.checkTradeValue());
		} else {
			assertFalse(tradeValue.checkTradeValue());
		}
	}
}
