package com.dhl.g05.trading;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dhl.g05.gameplayconfig.TradingModel;

public class TradeValueTest {

	MockTradeConfig tradeMock = new MockTradeConfig();
	TradingModel trade = tradeMock.TradingModelTest();

	@Test
	public void generateRandomValueTest() {

		TradeValue tradeValue = new TradeValue(trade);
		double randomValue = tradeValue.generateRandomValue();
		assertTrue(randomValue >= 0.00 && randomValue <= 1.00);
	}

	@Test
	public void checkTradeValue() {
		TradeValue tradeValue = new TradeValue(trade);
		double randomValue = tradeValue.generateRandomValue();
		if (randomValue <= 1.0) {
			assertTrue(tradeValue.checkTradeValue());
		} else {
			assertFalse(tradeValue.checkTradeValue());
		}
	}
}