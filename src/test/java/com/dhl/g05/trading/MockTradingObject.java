package com.dhl.g05.trading;

import com.dhl.g05.leaguemodel.gameplayconfig.TradingModel;


public class MockTradingObject{
	
	public  TradingModel TradingModelTest() {
		TradingModel trading = new TradingModel() ;
		trading.setLossPoint(8);
		trading.setMaxPlayersPerTrade(3);
		trading.setRandomTradeOfferChance(1.00);
		trading.setRandomAcceptanceChance(0.05);
		return trading;
	}

}
