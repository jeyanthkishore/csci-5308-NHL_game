package com.dhl.g05.trading;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.ApplicationTestConfiguration;
import com.dhl.g05.simulation.statemachine.ITradingConfig;

public class TradeValueTest {

	private static TradingMockAbstractFactory tradingMockFactory;

	@BeforeClass
	public static void init() {
		tradingMockFactory = ApplicationTestConfiguration.instance().getTradingMockConcreteFactoryState();
	}

	MockLeagueModel mockLeague = tradingMockFactory.createMockLeagueModel();
	ITradingConfig trade = mockLeague.tradingConfigMock();

	@Test
	public void generateRandomValueTest() {
		ITradeValue tradeValue = ApplicationConfiguration.instance().getTradingConcreteFactoryState()
				.createTradeValue(trade);
		double randomValue = tradeValue.generateRandomValue();
		assertTrue(randomValue >= 0.00 && randomValue <= 1.00);
	}

	@Test
	public void checkTradeValue() {
		ITradeValue tradeValue = ApplicationConfiguration.instance().getTradingConcreteFactoryState()
				.createTradeValue(trade);
		double randomValue = tradeValue.generateRandomValue();
		if (randomValue <= 1.0) {
			assertFalse(tradeValue.checkTradeValue());
		}
	}
}
