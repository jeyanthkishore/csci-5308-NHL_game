package com.dhl.g05.trading;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.dhl.g05.model.IConference;
import com.dhl.g05.model.IDivision;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.simulation.ITradingConfig;

public class InitiateTradeOffer implements IIntiateTradeOffer {
	
	static final Logger logger = LogManager.getLogger(TradeDecision.class);
	private ITradingConfig trade;

	public ITradingConfig getTrade() {
		return trade;
	}

	public void setTrade(ITradingConfig trade) {
		this.trade = trade;
	}

	public ILeague initiateTradeOffer(ILeague league) {
		boolean hasBestTeamToTrade = false;
		IWeakTeam teamInitiatingTrade = AbstractTradingFactory.instance().getWeakteam();
		IStrongTeam teamAcceptingTrade = AbstractTradingFactory.instance().getStrongteam();
		ITradeDecision tradeDecision = AbstractTradingFactory.instance().getTradedecision();
		ITradingConfig trade = getTrade();
		ITradeValue checkTradeValue = new TradeValue(trade);

		for (IConference c : league.getConferenceDetails()) {
			for (IDivision d : c.getDivisionDetails()) {
				for (ITeam t : d.getTeamDetails()) {
					if (t.getLossCount() >= trade.getLossPoint() == true) {
						if (checkTradeValue.checkTradeValue() == true) {
							if (t.getUserTeam() == false) {
							}
						}
						logger.info("Team initiating trade is "+ t.getTeamName());
						teamInitiatingTrade.setWeakTeam(t);
						teamInitiatingTrade.setConferenceName(c.getConferenceName());
						teamInitiatingTrade.setDivisionName(d.getDivisionName());
						teamInitiatingTrade.playersToOffer(trade);
						hasBestTeamToTrade = teamAcceptingTrade.findTeamToSwap(league);
						if (hasBestTeamToTrade == true) {
							tradeDecision.TradeResult(trade);
						} else 
							logger.info("No suitable Team to trade with");
							break;
						}
					}
				}
			}
		return league;
	}
}
