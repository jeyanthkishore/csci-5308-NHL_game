package com.dhl.g05.trading;

import java.util.Random;

import com.dhl.g05.gameplayconfig.TradingModel;

public class TradeValue {

	private double randomTradeValue;
	private double tradeChanceInConfig;
	 

	public TradeValue(TradingModel trade) {
		this.tradeChanceInConfig = trade.getRandomTradeOfferChance();
	}
	
	public double generateRandomValue() {
		Random randomValue = new Random();
		randomTradeValue = randomValue.nextDouble();
		return randomTradeValue;
	}

	public boolean checkTradeValue() {
		randomTradeValue = generateRandomValue();
		if (randomTradeValue <= tradeChanceInConfig)
			return true;
		else
			return false;
	}
}
