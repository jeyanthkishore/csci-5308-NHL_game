package com.dhl.g05.trading;

import com.dhl.g05.gamePlayConfig.TradingModel;


public class MockTradingObject{
	
	public  TradingModel TradingModelTest() {
		TradingModel trading = new TradingModel() ;
		trading.setLossPoint(8);
		trading.setMaxPlayersPerTrade(2);
		trading.setRandomTradeOfferChance(0.05);
		trading.setRandomAcceptanceChance(0.05);
		return trading;
	}

}
