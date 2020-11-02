package com.dhl.g05.gameplayconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dhl.g05.mockdata.JsonMockDataDb;

public class TradingModelTest {

	@Test
	public void parameterConstructorTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		TradingModel object = new TradingModel(data.lossPoint, data.randomTradeOffer, data.maxPlayerPerTrade,
				data.randomAcceptanceChance);
		assertEquals(data.randomTradeOffer, object.getRandomTradeOfferChance(), 0);
		assertSame(data.lossPoint, object.getLossPoint());
	}

	@Test
	public void getLossPointTest() {
		TradingModel object = new TradingModel();
		object.setLossPoint(5);
		assertEquals(object.getLossPoint(), 5);
	}

	@Test
	public void getRandomTradeOfferChanceTest() {
		TradingModel object = new TradingModel();
		object.setRandomTradeOfferChance(0.05);
		assertEquals(object.getRandomTradeOfferChance(), 0.05, 0);
	}

	@Test
	public void getMaxPlayersPerTradeTest() {
		TradingModel object = new TradingModel();
		object.setMaxPlayersPerTrade(2);
		assertSame(object.getMaxPlayersPerTrade(), 2);
	}

	@Test
	public void getRandomAcceptanceChanceTest() {
		TradingModel object = new TradingModel();
		object.setRandomAcceptanceChance(0.09);
		assertEquals(object.getRandomAcceptanceChance(), 0.09, 0);
	}

	@Test
	public void isValidLossPointTest() {
		TradingModel object = new TradingModel();
		object.setLossPoint(-1);
		assertTrue(object.isNotValidLossPoint());
	}

	@Test
	public void isValidTradeOfferChanceTest() {
		TradingModel object = new TradingModel();
		object.setRandomAcceptanceChance(2.99);
		assertTrue(object.isNotValidAcceptanceChance());
	}

	@Test
	public void isValidMaxplayerPerTradeTest() {
		TradingModel object = new TradingModel();
		object.setMaxPlayersPerTrade(-10);
		assertTrue(object.isNotValidMaxplayerPerTrade());
	}
	
	@Test
	public void isNotValidAcceptanceChanceTest() {
		TradingModel object = new TradingModel();
		object.setRandomAcceptanceChance(-10);
		assertTrue(object.isNotValidAcceptanceChance());
	}
	
	@Test
	public void validateTest() {
		TradingModel object = new TradingModel();
		object.setLossPoint(-1);
		assertSame(TradingConstant.LossError,object.validate());
		object = new TradingModel();
		object.setRandomAcceptanceChance(2.99);
		assertSame(TradingConstant.AcceptanceError,object.validate());
		object = new TradingModel();
		object.setMaxPlayersPerTrade(-10);
		assertSame(TradingConstant.MaxPlayerError,object.validate());
		object = new TradingModel();
		object.setRandomAcceptanceChance(-10);
		assertSame(TradingConstant.AcceptanceError,object.validate());
		object = new TradingModel(4, 0.2, 2, 0.4);
		assertSame(TradingConstant.Success,object.validate());
	}

}
