package com.dhl.g05.trading;

import com.dhl.g05.gameplayconfig.TradingModel;

public class MockTradeConfig{
	
	public  TradingModel TradingModelTest() {
		TradingModel trading = new TradingModel() ;
		trading.setLossPoint(8);
		trading.setMaxPlayersPerTrade(2);
		trading.setRandomTradeOfferChance(1.00);
		trading.setRandomAcceptanceChance(0.05);
		return trading;
	}

}
