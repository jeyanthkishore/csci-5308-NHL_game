package com.dhl.g05.trading;

import com.dhl.g05.leaguemodel.gameplayconfig.TradingModel;

public class CheckTradeValue extends GenerateRandomTradeValue {
	private double randomTradeOffer;
	private double tradeChanceInConfig;

	public CheckTradeValue(TradingModel trade) {
		this.randomTradeOffer = generateRandomValue();
		this.tradeChanceInConfig = trade.getRandomTradeOfferChance();
	}

	public boolean checkTradeValue() {
		if (randomTradeOffer <= tradeChanceInConfig)
			return true;
		else
			return false;
	}
}
