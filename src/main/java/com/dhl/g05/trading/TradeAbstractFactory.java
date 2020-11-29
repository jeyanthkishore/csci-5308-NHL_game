package com.dhl.g05.trading;

public abstract class TradeAbstractFactory {

	public static TradeAbstractFactory abstractTradingFactory;

	private static TradeAbstractFactory uniqueInstance = null;

	public static TradeAbstractFactory instance() {
		if (uniqueInstance == null) {
			uniqueInstance = new TradingFactory();
		}
		return uniqueInstance;
	}

	public static void setFactory(TradeAbstractFactory abstractTradingFactory) {
		uniqueInstance = abstractTradingFactory;
	}

	public static TradeAbstractFactory getFactory() {
		return uniqueInstance;
	}

	public abstract ITradeDecision getTradedecision();

	public abstract ISortPlayerStrength getSortplayerstrength();

	public abstract IStrongTeam getStrongteam();

	public abstract ISwapPlayers getSwapplayers();

	public abstract IWeakTeam getWeakteam();

	public abstract IResolveTrade getResolveTrade();

	public abstract IIntiateTradeOffer getInititatetradeoffer();

}
