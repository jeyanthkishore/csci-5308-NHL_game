package com.dhl.g05.trading;

import java.util.Random;

import com.dhl.g05.gameplayconfig.TradingModel;
import com.dhl.g05.player.PlayerModel;
import com.dhl.g05.team.TeamModel;

public class TradeDecision implements ITradeDecision {

	public boolean TradeResult(TeamModel weakTeam, TeamModel strongTeam) {
        boolean tradeResult=false;
		Random random = new Random();
		double ramdomTradeChance = 0.0;
		TradingModel trade = new TradingModel();
		ramdomTradeChance = random.nextDouble();
		int captain = 0;
		if (ramdomTradeChance >= trade.getRandomAcceptanceChance()) {
			{
				if(weakTeam.getUserTeam())
				{
					//ask other user to take a decision
					//tradeResult=true or false;
				}
				else
				{
					tradeResult=true; 
				}
			}
			return tradeResult;
	
		} else {

			return tradeResult;
		}

	}
}
