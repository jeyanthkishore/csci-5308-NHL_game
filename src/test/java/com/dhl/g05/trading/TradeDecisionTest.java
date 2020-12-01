package com.dhl.g05.trading;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.ApplicationTestConfiguration;
import com.dhl.g05.simulation.statemachine.ITradingConfig;

public class TradeDecisionTest {

	private static TradingMockAbstractFactory tradingMockFactory;

	@BeforeClass
	public static void init() {
		tradingMockFactory = ApplicationTestConfiguration.instance().getTradingMockConcreteFactoryState();
	}
	
	MockLeagueModel mockLeague = tradingMockFactory.createMockLeagueModel();
	
	@Test
	public void tadeAcceptedTest() {

		ITradingConfig tradingConfig = mockLeague.tradingConfigMock();
		ITradeDecision tradeDecision = ApplicationConfiguration.instance().getTradingConcreteFactoryState()
				.createTradedecision();
		IWeakTeam teamInitiatingTrade = mockLeague.weakTeamMock();
		IStrongTeam teamAcceptingTrade = mockLeague.strongTeamMock1();
		tradeDecision.TradeResult(tradingConfig, teamInitiatingTrade, teamAcceptingTrade);
		boolean result = tradeDecision.getTradeDecision();
		assertTrue(result);
	}

	@Test
	public void tradeRejectedTest() {
		ITradingConfig tradingConfig = mockLeague.tradingConfigMock();
		ITradeDecision tradeDecision = ApplicationConfiguration.instance().getTradingConcreteFactoryState()
				.createTradedecision();
		IWeakTeam teamInitiatingTrade = mockLeague.weakTeamMock();
		IStrongTeam teamAcceptingTrade = mockLeague.strongTeamMock2();
		tradeDecision.TradeResult(tradingConfig, teamInitiatingTrade, teamAcceptingTrade);
		boolean result = tradeDecision.getTradeDecision();
		assertFalse(result);
	}

}
