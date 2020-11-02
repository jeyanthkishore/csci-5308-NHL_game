package com.dhl.g05.trading;

import java.util.Random;

import com.dhl.g05.gameplayconfig.TradingModel;
import com.dhl.g05.team.TeamModel;


public class AcceptRejectTrade implements IAcceptRejectTrade {

	public boolean TradeResult(TeamModel weakTeam, TeamModel strongTeam) {

		double ramdomTradeChance = 0.0;
		boolean setTradeResult;
		TradingModel trade = new TradingModel();
		Random random = new Random();
		ramdomTradeChance = random.nextDouble();

		// boolean isUserTeam=false
		// include condition weakTeams user then call display function and get the
		// result from cli
		if (ramdomTradeChance >= trade.getRandomAcceptanceChance()) {
			//set Losscount to zero
			//weakTeam.setLossCount=0;
			setTradeResult= true;
		} else {
			setTradeResult= false;
		}
		return setTradeResult; 

	}
}
