package com.dhl.g05.trading;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.communication.ITradeCommunication;
import com.dhl.g05.simulation.statemachine.ITradingConfig;

public class TradeDecision implements ITradeDecision {
	
	static final Logger logger = LogManager.getLogger(TradeDecision.class);
	private boolean TradeDecision;

	public boolean getTradeDecision() {
		return TradeDecision;
	}

	public void setTradeDecision(boolean tradeDecision) {
		TradeDecision = tradeDecision;
	}

	public void TradeResult(ITradingConfig trade, IWeakTeam teamInitiatingTrade,IStrongTeam teamAcceptingTrade ) {

		int response = 0;
		Random random = new Random();
		double ramdomTradeChance = 0.0;
		boolean tradeAccepeted = false;
		ramdomTradeChance = random.nextDouble();
		ITradeCommunication showDetails = ApplicationConfiguration.instance().getCommunicationConcreteFactoryState().getTradeCommunication();
		ISwapPlayers swap = ApplicationConfiguration.instance().getTradingConcreteFactoryState().createSwapplayers();

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
		} else {
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
		setTradeDecision(tradeAccepeted);
		if (tradeAccepeted == true) {
			teamInitiatingTrade.getWeakTeam().setLossCount(0);
			logger.info("Trade Accepted");
			swap.swapPlayers(teamInitiatingTrade.getWeakTeam(), teamAcceptingTrade.getStrongTeam(),
					teamInitiatingTrade.getPlayersOffered(), teamAcceptingTrade.getStrongestPlayersToTrade());

		}
	}
}
