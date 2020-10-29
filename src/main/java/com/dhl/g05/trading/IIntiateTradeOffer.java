package com.dhl.g05.trading;

import com.dhl.g05.leaguemodel.gameplayconfig.TradingModel;
import com.dhl.g05.leaguemodel.league.LeagueModel;


public interface IIntiateTradeOffer {

	public TradingModel getTrade();

	public void setTrade(TradingModel trade);

	public LeagueModel initiateTradeOffer(LeagueModel league);
}
