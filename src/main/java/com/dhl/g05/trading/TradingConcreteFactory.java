package com.dhl.g05.trading;

import com.dhl.g05.simulation.statemachine.ITradingConfig;

public class TradingConcreteFactory extends TradingAbstractFactory {

	@Override
	public ITradeDecision createTradedecision() {
		return new TradeDecision();
	}

	@Override
	public ISortPlayerStrength createSortplayerstrength() {
		return new SortPlayerStrength();
	}

	@Override
	public IStrongTeam createStrongteam() {
		return new StrongTeam();
	}

	@Override
	public ISwapPlayers createSwapplayers() {
		return new SwapPlayers();
	}

	@Override
	public IWeakTeam createWeakteam() {
		return new WeakTeam();

	}

	@Override
	public ITradePickToPlayerDraft createTradePickToPlayerDraft() {
		return new TradePickToPlayerDraft();
	}

	@Override
	public IIntiateTradeOffer createInititatetradeoffer() {
		return new InitiateTradeOffer();
	}

	@Override
	public ITradeValue createTradeValue(ITradingConfig trade) {
		return new TradeValue();
	}


}
