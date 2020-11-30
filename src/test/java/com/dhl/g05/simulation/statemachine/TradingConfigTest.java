package com.dhl.g05.simulation.statemachine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.simulation.SimulationAbstractFactory;

public class TradingConfigTest {

	private static SimulationAbstractFactory simulationAbstractFactory;

	@BeforeClass
	public static void init() {
		simulationAbstractFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
	}

	@Test
	public void getLossPointTest() {
		ITradingConfig tradingConfig = simulationAbstractFactory.createTradingConfig();
		tradingConfig.setLossPoint(5);
		assertEquals(tradingConfig.getLossPoint(), 5);
	}

	@Test
	public void getRandomTradeOfferChanceTest() {
		ITradingConfig tradingConfig = simulationAbstractFactory.createTradingConfig();
		tradingConfig.setRandomTradeOfferChance(0.05);
		assertEquals(tradingConfig.getRandomTradeOfferChance(), 0.05, 0);
	}

	@Test
	public void getMaxPlayersPerTradeTest() {
		ITradingConfig tradingConfig = simulationAbstractFactory.createTradingConfig();
		tradingConfig.setMaxPlayersPerTrade(2);
		assertSame(tradingConfig.getMaxPlayersPerTrade(), 2);
	}

	@Test
	public void getRandomAcceptanceChanceTest() {
		ITradingConfig tradingConfig = simulationAbstractFactory.createTradingConfig();
		tradingConfig.setRandomAcceptanceChance(0.09);
		assertEquals(tradingConfig.getRandomAcceptanceChance(), 0.09, 0);
	}

	@Test
	public void isValidLossPointTest() {
		ITradingConfig tradingConfig = simulationAbstractFactory.createTradingConfig();
		tradingConfig.setLossPoint(-1);
		assertTrue(tradingConfig.isNotValidLossPoint());
	}

	@Test
	public void isValidTradeOfferChanceTest() {
		ITradingConfig tradingConfig = simulationAbstractFactory.createTradingConfig();
		tradingConfig.setRandomAcceptanceChance(2.99);
		assertTrue(tradingConfig.isNotValidAcceptanceChance());
	}

	@Test
	public void isValidMaxplayerPerTradeTest() {
		ITradingConfig tradingConfig = simulationAbstractFactory.createTradingConfig();
		tradingConfig.setMaxPlayersPerTrade(-10);
		assertTrue(tradingConfig.isNotValidMaxplayerPerTrade());
	}

	@Test
	public void isNotValidAcceptanceChanceTest() {
		ITradingConfig tradingConfig = simulationAbstractFactory.createTradingConfig();
		tradingConfig.setRandomAcceptanceChance(-10);
		assertTrue(tradingConfig.isNotValidAcceptanceChance());
	}

	@Test
	public void validateTest1() {
		ITradingConfig tradingConfig = simulationAbstractFactory.createTradingConfig();
		tradingConfig.setLossPoint(-1);
		assertSame(TradingConstant.LossError, tradingConfig.validate());
	}

	public void validateTest2() {
		ITradingConfig tradingConfig = simulationAbstractFactory.createTradingConfig();
		tradingConfig.setRandomAcceptanceChance(2.99);
		assertSame(TradingConstant.AcceptanceError, tradingConfig.validate());
	}

	public void validateTest3() {
		ITradingConfig tradingConfig = simulationAbstractFactory.createTradingConfig();
		tradingConfig.setMaxPlayersPerTrade(-10);
		assertSame(TradingConstant.MaxPlayerError, tradingConfig.validate());
	}

	public void validateTest4() {
		ITradingConfig tradingConfig = simulationAbstractFactory.createTradingConfig();
		tradingConfig.setRandomAcceptanceChance(-10);
		assertSame(TradingConstant.AcceptanceError, tradingConfig.validate());
	}
}
