package com.dhl.g05.trading;

import java.util.Random;

import com.dhl.g05.gameplayconfig.ITradingConfig;

public class TradeValue implements ITradeValue {

	private double randomTradeValue;
	private double tradeChanceInConfig;

	public TradeValue(ITradingConfig trade) {
		this.tradeChanceInConfig = trade.getRandomTradeOfferChance();
	}

	public double generateRandomValue() {
		Random randomValue = new Random();
		randomTradeValue = randomValue.nextDouble();
		return randomTradeValue;
	}

	public boolean checkTradeValue() {
		randomTradeValue = generateRandomValue();
		if (randomTradeValue <= tradeChanceInConfig) {
			return true;
		} else {
			return false;
		}
	}
}
