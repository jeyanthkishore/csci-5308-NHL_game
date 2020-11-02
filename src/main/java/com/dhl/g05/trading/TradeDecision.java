package com.dhl.g05.trading;

import java.util.Random;

import com.dhl.g05.communication.ITradeCommunication;
import com.dhl.g05.communication.PlayerCommunication;
import com.dhl.g05.gameplayconfig.TradingModel;


public class TradeDecision implements ITradeDecision {

	public void TradeResult(TradingModel trade) {

		int response = 0;
		Random random = new Random();
		double ramdomTradeChance = 0.0;
		boolean tradeAccepeted = false;
		ramdomTradeChance = random.nextDouble();
		ITradeCommunication showDetails = new PlayerCommunication();
		ISwapPlayers swap = TradingConfig.instance().getSwapplayers();
		IWeakTeam teamInitiatingTrade = TradingConfig.instance().getWeakteam();
		IStrongTeam teamAcceptingTrade = TradingConfig.instance().getStrongteam();

		if (teamAcceptingTrade.getStrongTeam().getUserTeam() == true) {
			showDetails.sendTradeMessage(teamInitiatingTrade.getPlayersOffered(),
					teamAcceptingTrade.getStrongestPlayersToTrade());
			response = showDetails.getTradeDecision();
			do {
				if (response == 1) {
					showDetails.sendMessage("You have decided to accept the trade! Bravo !");
					tradeAccepeted = true;
					break;
				} else if (response == 2) {
					showDetails.sendMessage("You have decided not to accept the trade! Good Decision !");
					tradeAccepeted = false;
					break;
				} else {
					showDetails.sendMessage("OOPS invalid entry, try again !");
					response = showDetails.getTradeDecision();
				}
			} while (response == 1 || response == 2);
		}

		else {
			if (teamAcceptingTrade.getStrengthOfStrongestPlayers() > teamInitiatingTrade
					.getStrengthOfPlayersOffered()) {
				tradeAccepeted = true;
				teamAcceptingTrade.setStrengthOfStrongestPlayers(0.00);
			} else {
				if (ramdomTradeChance >= trade.getRandomAcceptanceChance()) {
					tradeAccepeted = true;
				}

			}
		}
		if (tradeAccepeted == true) {
			teamInitiatingTrade.getWeakTeam().setLossCount(0);
			swap.swapPlayers(teamInitiatingTrade.getWeakTeam(), teamAcceptingTrade.getStrongTeam(),
					teamInitiatingTrade.getPlayersOffered(), teamAcceptingTrade.getStrongestPlayersToTrade());

		}
	}
}
