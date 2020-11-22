package com.dhl.g05.trading;

import com.dhl.g05.gameplayconfig.ITradingConfig;
import com.dhl.g05.league.ILeague;

public interface IIntiateTradeOffer {

	public ITradingConfig getTrade();

	public void setTrade(ITradingConfig trade);

	public ILeague initiateTradeOffer(ILeague league);
}
