package com.dhl.g05.trading;

public class TradingFactory extends AbstractTradingFactory {

	@Override
	public ITradeDecision getTradedecision() {
		return  new TradeDecision();
	}

	@Override
	public ISortPlayerStrength getSortplayerstrength() {
		return new SortPlayerStrength();
	}

	@Override
	public IStrongTeam getStrongteam() {
		return  new StrongTeam();
	}

	@Override
	public ISwapPlayers getSwapplayers() {
		return new SwapPlayers();
	}

	@Override
	public IWeakTeam getWeakteam() {
		return new WeakTeam();
	}

	@Override
	public IResolveTrade getResolveTrade() {
		return new ResolveTrade();
	}

	@Override
	public IIntiateTradeOffer getInititatetradeoffer() {
		return new InitiateTradeOffer();
	}

}
