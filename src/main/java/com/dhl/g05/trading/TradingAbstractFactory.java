package com.dhl.g05.trading;

import com.dhl.g05.simulation.statemachine.ITradingConfig;

public abstract class TradingAbstractFactory {
	
	private static TradingAbstractFactory abstractTradingFactory;

	public static TradingAbstractFactory instance(TradingState trade) {
		abstractTradingFactory = trade.concreteMethod();
		return abstractTradingFactory;
	}

	public abstract ITradeDecision createTradedecision();

	public abstract ISortPlayerStrength createSortplayerstrength();

	public abstract IStrongTeam createStrongteam();

	public abstract ISwapPlayers createSwapplayers();

	public abstract IWeakTeam createWeakteam();

	public abstract ITradePickToPlayerDraft createTradePickToPlayerDraft();

	public abstract IIntiateTradeOffer createInititatetradeoffer();
	
	public abstract ITradeValue createTradeValue (ITradingConfig trade);
	
	}

