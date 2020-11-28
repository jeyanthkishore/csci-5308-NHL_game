package com.dhl.g05.trading;

import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.gameplayconfig.ITradingConfig;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.team.ITeam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
						logger.info("Validating player details");
						teamInitiatingTrade.setWeakTeam(t);
						teamInitiatingTrade.setConferenceName(c.getConferenceName());
						teamInitiatingTrade.setDivisionName(d.getDivisionName());
						teamInitiatingTrade.playersToOffer(trade);
						hasBestTeamToTrade = teamAcceptingTrade.findTeamToSwap(league);
						if (hasBestTeamToTrade == true) {
							tradeDecision.TradeResult(trade);
						} else 
							break;
						}

					}
				}
			}

		return league;
	}
}
