package com.dhl.g05.gameplayconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.mockdata.JsonMockDataDb;

public class TradingConfigTest {

	private static ITradingConfig tradingConfig;
	private static AbstractGamePlayConfigFactory abstractGamePlayConfigFactory;

	@BeforeClass
	public static void setup() {
		AbstractGamePlayConfigFactory.setFactory(new GamePlayConfigFactory());
		abstractGamePlayConfigFactory = AbstractGamePlayConfigFactory.getFactory();
	}

	@Test
	public void parameterConstructorTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		ITradingConfig object = new TradingConfig(data.lossPoint, data.randomTradeOffer, data.maxPlayerPerTrade,
				data.randomAcceptanceChance);
		assertEquals(data.randomTradeOffer, object.getRandomTradeOfferChance(), 0);
		assertSame(data.lossPoint, object.getLossPoint());
	}

	@Test
	public void getLossPointTest() {
		tradingConfig = abstractGamePlayConfigFactory.getTradingConfig();
		tradingConfig.setLossPoint(5);
		assertEquals(tradingConfig.getLossPoint(), 5);
	}

	@Test
	public void getRandomTradeOfferChanceTest() {
		tradingConfig = abstractGamePlayConfigFactory.getTradingConfig();
		tradingConfig.setRandomTradeOfferChance(0.05);
		assertEquals(tradingConfig.getRandomTradeOfferChance(), 0.05, 0);
	}

	@Test
	public void getMaxPlayersPerTradeTest() {
		tradingConfig = abstractGamePlayConfigFactory.getTradingConfig();
		tradingConfig.setMaxPlayersPerTrade(2);
		assertSame(tradingConfig.getMaxPlayersPerTrade(), 2);
	}

	@Test
	public void getRandomAcceptanceChanceTest() {
		tradingConfig = abstractGamePlayConfigFactory.getTradingConfig();
		tradingConfig.setRandomAcceptanceChance(0.09);
		assertEquals(tradingConfig.getRandomAcceptanceChance(), 0.09, 0);
	}

	@Test
	public void isValidLossPointTest() {
		tradingConfig = abstractGamePlayConfigFactory.getTradingConfig();
		tradingConfig.setLossPoint(-1);
		assertTrue(tradingConfig.isNotValidLossPoint());
	}

	@Test
	public void isValidTradeOfferChanceTest() {
		tradingConfig = abstractGamePlayConfigFactory.getTradingConfig();
		tradingConfig.setRandomAcceptanceChance(2.99);
		assertTrue(tradingConfig.isNotValidAcceptanceChance());
	}

	@Test
	public void isValidMaxplayerPerTradeTest() {
		tradingConfig = abstractGamePlayConfigFactory.getTradingConfig();
		tradingConfig.setMaxPlayersPerTrade(-10);
		assertTrue(tradingConfig.isNotValidMaxplayerPerTrade());
	}

	@Test
	public void isNotValidAcceptanceChanceTest() {
		tradingConfig = abstractGamePlayConfigFactory.getTradingConfig();
		tradingConfig.setRandomAcceptanceChance(-10);
		assertTrue(tradingConfig.isNotValidAcceptanceChance());
	}

	@Test
	public void validateTest1() {
		tradingConfig = abstractGamePlayConfigFactory.getTradingConfig();
		tradingConfig.setLossPoint(-1);
		assertSame(TradingConstant.LossError, tradingConfig.validate());
	}

	public void validateTest2() {
		tradingConfig = abstractGamePlayConfigFactory.getTradingConfig();
		tradingConfig.setRandomAcceptanceChance(2.99);
		assertSame(TradingConstant.AcceptanceError, tradingConfig.validate());
	}

	public void validateTest3() {
		tradingConfig = abstractGamePlayConfigFactory.getTradingConfig();
		tradingConfig.setMaxPlayersPerTrade(-10);
		assertSame(TradingConstant.MaxPlayerError, tradingConfig.validate());
	}

	public void validateTest4() {
		tradingConfig = abstractGamePlayConfigFactory.getTradingConfig();
		tradingConfig.setRandomAcceptanceChance(-10);
		assertSame(TradingConstant.AcceptanceError, tradingConfig.validate());
	}
}
