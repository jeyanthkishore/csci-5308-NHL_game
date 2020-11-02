package com.dhl.g05.trading;

import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.gameplayconfig.TradingModel;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.team.TeamModel;

public class InitiateTradeOffer implements IIntiateTradeOffer {

	private TradingModel trade;

	public TradingModel getTrade() {
		return trade;
	}

	public void setTrade(TradingModel trade) {
		this.trade = trade;
	}

	public void initiateTradeOffer(LeagueModel league) {
		boolean hasBestTeamToTrade = false;
		IWeakTeam teamInitiatingTrade = TradingConfig.instance().getWeakteam();
		IStrongTeam teamAcceptingTrade = TradingConfig.instance().getStrongteam();
		ITradeDecision tradeDecision = TradingConfig.instance().getTradedecision();
		TradingModel trade = getTrade();
		TradeValue checkTradeValue = new TradeValue(trade);
		LossPoint losspoint = new LossPoint();
		losspoint.setLossPoint(trade.getLossPoint());

		for (ConferenceModel c : league.getConferenceDetails()) {
			for (DivisionModel d : c.getDivisionDetails()) {
				for (TeamModel t : d.getTeamDetails()) {
					losspoint.setLossCount(t.getLossCount());
					if (losspoint.checkLossPoint() == true && checkTradeValue.checkTradeValue() == true
							&& t.getUserTeam() == false) {

						teamInitiatingTrade.setWeakTeam(t);
						teamInitiatingTrade.setConferenceName(c.getConferenceName());
						teamInitiatingTrade.setDivisionName(d.getDivisionName());
						teamInitiatingTrade.playersToOffer(trade);
						hasBestTeamToTrade = teamAcceptingTrade.findTeamToSwap(league);
						if (hasBestTeamToTrade == true) {
							// call trade decision to know the outcome of the initiated trade
							tradeDecision.TradeResult(trade);
						} else {
							// no team suitable for trading
							break;
						}

					}
				}
			}
		}
	}
}
