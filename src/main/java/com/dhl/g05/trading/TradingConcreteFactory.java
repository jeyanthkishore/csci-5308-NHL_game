package com.dhl.g05.trading;

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
	public IResolveTrade createResolveTrade() {
		return new ResolveTrade();
	}

	@Override
	public IIntiateTradeOffer createInititatetradeoffer() {
		return new InitiateTradeOffer();
	}
}
