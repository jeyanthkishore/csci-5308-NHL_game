package com.dhl.g05.trading;

public abstract class AbstractTradingFactory {

    public static AbstractTradingFactory abstractTradingFactory;

    public static AbstractTradingFactory getFactory() {
        return abstractTradingFactory;
    }

    public static void setFactory(AbstractTradingFactory abstractTradingFactory) {
    	AbstractTradingFactory.abstractTradingFactory = abstractTradingFactory;
    }

    public abstract ITradeDecision getTradedecision();

    public abstract ISortPlayerStrength getSortplayerstrength();

    public abstract  IStrongTeam getStrongteam();

    public abstract ISwapPlayers getSwapplayers();

    public abstract IWeakTeam getWeakteam();

    public abstract IResolveTrade getResolveTrade();
 
    public abstract IIntiateTradeOffer getInititatetradeoffer();

}
