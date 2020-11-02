package com.dhl.g05.trading;

import java.util.ArrayList;
import java.util.List;

import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.gameplayconfig.TradingModel;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.player.PlayerModel;
import com.dhl.g05.team.TeamModel;

public class InitiateTradeOffer implements IIntiateTradeOffer {

	private TradingModel trade;

	public TradingModel getTrade() {
		return trade;
	}

	public void setTrade(TradingModel trade) {
		this.trade = trade;
	}

	public LeagueModel initiateTradeOffer(LeagueModel league) {
		boolean tradeResult = false;
		IWeakTeam teamInitiatingTrade = TradingConfig.instance().getWeakteam();
		ICheckLossPoint lossPoint = TradingConfig.instance().getChecklosspoint();
		IStrongTeam teamAcceptingTrade = TradingConfig.instance().getStrongteam();
		IAcceptRejectTrade decision = TradingConfig.instance().getAcceptreject();
		ISwapPlayers swap = TradingConfig.instance().getSwapplayers();
		IResolveTrade resolveTrade = TradingConfig.instance().getResolvetrade();
		TradingModel trade = getTrade();
		CheckTradeValue checkTradeValue = new CheckTradeValue(trade);
		List<PlayerModel> weakPlayersToTrade = new ArrayList<PlayerModel>();
		List<PlayerModel> strongPlayersToTrade = new ArrayList<PlayerModel>();
		lossPoint.setLossPoint(trade.getLossPoint());

		for (ConferenceModel c : league.getConferenceDetails()) {
			for (DivisionModel d : c.getDivisionDetails()) {
				for (TeamModel t : d.getTeamDetails()) {
					// dont have the lossCount;
					lossPoint.setLossCount(10);
					if (lossPoint.checkLossPoint() == true && checkTradeValue.checkTradeValue() == true) {
						TeamModel teamTrading = t;
						teamInitiatingTrade.setConferenceName(c.getConferenceName());
						teamInitiatingTrade.setDivisionName(d.getDivisionName());
						weakPlayersToTrade = teamInitiatingTrade.callWeakPlayersAndGetPosition(teamTrading, trade);
						strongPlayersToTrade = teamAcceptingTrade.findTeamToSwap(weakPlayersToTrade, teamTrading,
								league);
						tradeResult = decision.TradeResult(teamTrading, teamAcceptingTrade.getStrongTeam());

						if (tradeResult) {
							swap.swapPlayers(teamTrading, teamAcceptingTrade.getStrongTeam(), weakPlayersToTrade,
									strongPlayersToTrade);
						} else {
							System.out.println("trade rejected");
						}

					}

				}
			}
		}
		return league;

	}
// send the league object back to the simulation.

}
