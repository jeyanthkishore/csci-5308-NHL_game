package com.dhl.g05.trading;

import java.util.Random;

import com.dhl.g05.gamePlayConfig.TradingModel;
import com.dhl.g05.leaguemodel.ConferenceObject;
import com.dhl.g05.leaguemodel.DivisionObject;
import com.dhl.g05.leaguemodel.LeagueObject;
import com.dhl.g05.leaguemodel.TeamObject;


public class InitiateTradeOffer implements ITrading{

	LeagueObject league =  new LeagueObject();

	public boolean generateTradeOffer(LeagueObject league, TradingModel trade){
		boolean initiateTrade = false;
		if (checkLossPoint(league, trade) == true)
		{
			if(checkTradeValue(generateRandomValue(), trade) == true);
			 initiateTrade = true;
		}
		return initiateTrade;
	}

	private boolean checkLossPoint(LeagueObject league, TradingModel trade) {
		int lossPoint=trade.getLossPoint();
		int teamLossCount=10;
		boolean flag = false;
		for (ConferenceObject c: league.getConferenceDetails()) {
			for (DivisionObject d: c.getDivisionDetails()) {
				for (TeamObject t: d.getTeamDetails()) {
					//int teamLossCount=t.getTeamLossCount() ----- waiting for this value
					if (teamLossCount >= lossPoint)
						flag = true;
				}
			}
		}
		return flag;
	}

	private double generateRandomValue(){
		Random randomTrade = new Random();
		double ramdomeTradeValue= randomTrade.nextDouble();
		System.out.println(ramdomeTradeValue);
		return ramdomeTradeValue;
	}

	private boolean checkTradeValue(double ramdomeTradeValue, TradingModel trade ){
		if (ramdomeTradeValue<= trade.getRandomTradeOfferChance())
			return true;
		else 
			return false;
	}

}
