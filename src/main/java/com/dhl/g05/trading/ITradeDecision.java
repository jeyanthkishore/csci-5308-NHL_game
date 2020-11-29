package com.dhl.g05.trading;

import com.dhl.g05.simulation.ITradingConfig;

public interface ITradeDecision {

	public void TradeResult(ITradingConfig trade, IWeakTeam teamInitiatingTrade,IStrongTeam teamAcceptingTrade );
}
