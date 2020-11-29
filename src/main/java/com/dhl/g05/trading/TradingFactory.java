package com.dhl.g05.trading;

public class TradingFactory extends TradeAbstractFactory {

	private ITradeDecision tradeDecision;
	private ISortPlayerStrength sortPlayerStrength;
	private IStrongTeam strongTeam;
	private ISwapPlayers swapPlayers;
	private IWeakTeam weakTeam;
	private IResolveTrade resolveTrade;
	private IIntiateTradeOffer initiateTradeOffer;

	@Override
	public ITradeDecision getTradedecision() {
		if (tradeDecision == null) {
			tradeDecision = new TradeDecision();
		}
		return tradeDecision;
	}

	@Override
	public ISortPlayerStrength getSortplayerstrength() {
		if (sortPlayerStrength == null) {
			sortPlayerStrength = new SortPlayerStrength();
		}
		return sortPlayerStrength;
	}

	@Override
	public IStrongTeam getStrongteam() {
		if (strongTeam == null) {
			strongTeam = new StrongTeam();
		}
		return strongTeam;
	}

	@Override
	public ISwapPlayers getSwapplayers() {
		if (swapPlayers == null) {
			swapPlayers = new SwapPlayers();
		}
		return swapPlayers;
	}

	@Override
	public IWeakTeam getWeakteam() {
		if (weakTeam == null) {
			weakTeam = new WeakTeam();
		}
		return weakTeam;
	}

	@Override
	public IResolveTrade getResolveTrade() {
		if (resolveTrade == null) {
			resolveTrade = new ResolveTrade();
		}
		return resolveTrade;
	}

	@Override
	public IIntiateTradeOffer getInititatetradeoffer() {
		if (initiateTradeOffer == null) {
			initiateTradeOffer = new InitiateTradeOffer();
		}
		return initiateTradeOffer;
	}

}
