package com.dhl.g05.trading;

import com.dhl.g05.model.ILeague;
import com.dhl.g05.simulation.ITradingConfig;

public interface IIntiateTradeOffer {

	public ITradingConfig getTrade();

	public void setTrade(ITradingConfig trade);

	public ILeague initiateTradeOffer(ILeague league);
}
