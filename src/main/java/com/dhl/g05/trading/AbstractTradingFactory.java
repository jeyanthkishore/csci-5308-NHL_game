package com.dhl.g05.trading;

public abstract class AbstractTradingFactory {

	public static AbstractTradingFactory abstractTradingFactory;

	private static AbstractTradingFactory uniqueInstance = null;

	public static AbstractTradingFactory instance() {
		if (uniqueInstance == null) {
			uniqueInstance = new TradingFactory();
		}
		return uniqueInstance;

	}

	public static void setFactory(AbstractTradingFactory abstractTradingFactory) {
		uniqueInstance = abstractTradingFactory;
	}

	public static AbstractTradingFactory getFactory() {
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
