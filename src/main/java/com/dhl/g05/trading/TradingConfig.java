package com.dhl.g05.trading;

public class TradingConfig {

	private static TradingConfig uniqueInstance = null;

	private ISortPlayerStrength sortplayerstrength;
	private IStrongTeam strongteam;
	private ISwapPlayers swapplayers;
	private IWeakTeam weakteam;
	private ITradeDecision tradedecision;
	private IResolveTrade resolveTrade;

	public IResolveTrade getResolveTrade() {
		return resolveTrade;
	}

	public void setResolveTrade(IResolveTrade resolveTrade) {
		this.resolveTrade = resolveTrade;
	}

	private TradingConfig() {

		sortplayerstrength = new SortPlayerStrength();
		strongteam = new StrongTeam();
		swapplayers = new SwapPlayers();
		weakteam = new WeakTeam();
		tradedecision = new TradeDecision();
		resolveTrade = new ResolveTrade();

	}

	public ITradeDecision getTradedecision() {
		return tradedecision;
	}

	public void setTradedecision(ITradeDecision tradedecision) {
		this.tradedecision = tradedecision;
	}

	public ISortPlayerStrength getSortplayerstrength() {
		return sortplayerstrength;
	}

	public void setSortplayerstrength(ISortPlayerStrength sortplayerstrength) {
		this.sortplayerstrength = sortplayerstrength;
	}

	public IStrongTeam getStrongteam() {
		return strongteam;
	}

	public void setStrongteam(IStrongTeam strongteam) {
		this.strongteam = strongteam;
	}

	public ISwapPlayers getSwapplayers() {
		return swapplayers;
	}

	public void setSwapplayers(ISwapPlayers swapplayers) {
		this.swapplayers = swapplayers;
	}

	public IWeakTeam getWeakteam() {
		return weakteam;
	}

	public void setWeakteam(IWeakTeam weakteam) {
		this.weakteam = weakteam;
	}

	public static TradingConfig instance() {
		if (null == uniqueInstance) {
			uniqueInstance = new TradingConfig();
		}
		return uniqueInstance;

	}

}
