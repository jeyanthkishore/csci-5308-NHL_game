package com.dhl.g05.gameplayconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dhl.g05.mockdata.JsonMockDataDb;

public class TradingConfigTest {

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
		ITradingConfig object = new TradingConfig();
		object.setLossPoint(5);
		assertEquals(object.getLossPoint(), 5);
	}

	@Test
	public void getRandomTradeOfferChanceTest() {
		ITradingConfig object = new TradingConfig();
		object.setRandomTradeOfferChance(0.05);
		assertEquals(object.getRandomTradeOfferChance(), 0.05, 0);
	}

	@Test
	public void getMaxPlayersPerTradeTest() {
		ITradingConfig object = new TradingConfig();
		object.setMaxPlayersPerTrade(2);
		assertSame(object.getMaxPlayersPerTrade(), 2);
	}

	@Test
	public void getRandomAcceptanceChanceTest() {
		ITradingConfig object = new TradingConfig();
		object.setRandomAcceptanceChance(0.09);
		assertEquals(object.getRandomAcceptanceChance(), 0.09, 0);
	}

	@Test
	public void isValidLossPointTest() {
		ITradingConfig object = new TradingConfig();
		object.setLossPoint(-1);
		assertTrue(object.isNotValidLossPoint());
	}

	@Test
	public void isValidTradeOfferChanceTest() {
		ITradingConfig object = new TradingConfig();
		object.setRandomAcceptanceChance(2.99);
		assertTrue(object.isNotValidAcceptanceChance());
	}

	@Test
	public void isValidMaxplayerPerTradeTest() {
		ITradingConfig object = new TradingConfig();
		object.setMaxPlayersPerTrade(-10);
		assertTrue(object.isNotValidMaxplayerPerTrade());
	}

	@Test
	public void isNotValidAcceptanceChanceTest() {
		ITradingConfig object = new TradingConfig();
		object.setRandomAcceptanceChance(-10);
		assertTrue(object.isNotValidAcceptanceChance());
	}

	@Test
	public void validateTest1() {
		ITradingConfig object = new TradingConfig();
		object.setLossPoint(-1);
		assertSame(TradingConstant.LossError, object.validate());
	}

	public void validateTest2() {
		ITradingConfig object = new TradingConfig();
		object.setRandomAcceptanceChance(2.99);
		assertSame(TradingConstant.AcceptanceError, object.validate());
	}

	public void validateTest3() {
		ITradingConfig object = new TradingConfig();
		object.setMaxPlayersPerTrade(-10);
		assertSame(TradingConstant.MaxPlayerError, object.validate());
	}

	public void validateTest4() {

		ITradingConfig object = new TradingConfig();
		object.setRandomAcceptanceChance(-10);
		assertSame(TradingConstant.AcceptanceError, object.validate());
	}

	public void validateTest5() {
		ITradingConfig object = new TradingConfig(4, 0.2, 2, 0.4);
		assertSame(TradingConstant.Success, object.validate());
	}

}
