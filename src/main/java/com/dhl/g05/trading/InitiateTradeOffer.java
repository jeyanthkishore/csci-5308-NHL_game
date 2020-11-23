package com.dhl.g05.trading;

import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.freeagent.FreeAgentModel;
import com.dhl.g05.gameplayconfig.ITradingConfig;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.player.IPlayer;
import com.dhl.g05.team.ITeam;

public class InitiateTradeOffer implements IIntiateTradeOffer {

	private ITradingConfig trade;

	public ITradingConfig getTrade() {
		return trade;
	}

	public void setTrade(ITradingConfig trade) {
		this.trade = trade;
	}

	public ILeague initiateTradeOffer(ILeague league) {
		boolean hasBestTeamToTrade = false;
		IWeakTeam teamInitiatingTrade = Trading.instance().getWeakteam();
		IStrongTeam teamAcceptingTrade = Trading.instance().getStrongteam();
		ITradeDecision tradeDecision = Trading.instance().getTradedecision();
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
		for (IConference c : league.getConferenceDetails()) {
			for (IDivision d : c.getDivisionDetails()) {
				for (ITeam t : d.getTeamDetails()) {
					{
						System.out.println(t.getTeamName());
						for (IPlayer p : t.getPlayerList()) {
							System.out.println(((FreeAgentModel) p).getPlayerName() + " " + p.getPosition()
									+ p.getPlayerStrength());
						}
					}
				}
			}
		}
		return league;
	}
}
