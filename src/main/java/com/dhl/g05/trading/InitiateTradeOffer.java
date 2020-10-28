package com.dhl.g05.trading;

import java.util.ArrayList;
import java.util.Random;

import com.dhl.g05.gamePlayConfig.TradingModel;
import com.dhl.g05.leaguemodel.conference.ConferenceObject;
import com.dhl.g05.leaguemodel.division.DivisionObject;
import com.dhl.g05.leaguemodel.league.LeagueObject;
import com.dhl.g05.leaguemodel.team.TeamObject;


public class InitiateTradeOffer {

	private Object TradeAlgorithm;
	private LeagueObject league;
	private ConferenceObject conference;
	private DivisionObject division;
	private TeamObject team;
	public ConferenceObject getConference() {
		return conference;
	}

	public void setConference(ConferenceObject conference) {
		this.conference = conference;
	}

	public DivisionObject getDivision() {
		return division;
	}

	public void setDivision(DivisionObject division) {
		this.division = division;
	}

	public TeamObject getTeam() {
		return team;
	}

	public void setTeam(TeamObject team) {
		this.team = team;
	}

	private TradingModel trade;

	public Object getTradeAlgorithm() {
		return TradeAlgorithm;
	}

	public void setTradeAlgorithm(Object tradeAlgorithm) {
		TradeAlgorithm = tradeAlgorithm;
	}

	public LeagueObject getLeague() {
		return league;
	}

	public void setLeague(LeagueObject league) {
		this.league = league;
	}

	public TradingModel getTrade() {
		return trade;
	}

	public void setTrade(TradingModel trade) {
		this.trade = trade;
	}

	public ArrayList<String> initiateTradeOffer()
	{
		ArrayList <String> teamReadyToTrade= new ArrayList<String>();
		boolean checkLossPointResult=false;
		boolean CheckTradeValueResult=false;
		Random loss = new Random();
		CheckLossPoint lossPoint= new CheckLossPoint();
		lossPoint.setLossPoint(trade.getLossPoint());

		for (ConferenceObject c: league.getConferenceDetails()) 
		{
			for (DivisionObject d: c.getDivisionDetails()) 
			{
				for (TeamObject t: d.getTeamDetails())
				{ 
					lossPoint.setLossCount(10);
					checkLossPointResult=lossPoint.checkLossPoint();
					CheckTradeValue checkTradeValue= new CheckTradeValue(trade);
					CheckTradeValueResult=checkTradeValue.checkTradeValue();
					if(checkLossPointResult== true && CheckTradeValueResult==true)
					{
						teamReadyToTrade.add(t.getTeamName());

						 //call TradeAlgorithm
					}

				}
			}
		}
		return teamReadyToTrade;
	}

}
