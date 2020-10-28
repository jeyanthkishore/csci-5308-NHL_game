package com.dhl.g05.trading;

import java.util.ArrayList;
import java.util.Random;

import com.dhl.g05.gamePlayConfig.TradingModel;
import com.dhl.g05.leaguemodel.conference.ConferenceModel;
import com.dhl.g05.leaguemodel.division.DivisionModel;
import com.dhl.g05.leaguemodel.league.LeagueModel;
import com.dhl.g05.leaguemodel.team.TeamModel;


public class InitiateTradeOffer {

	private Object TradeAlgorithm;
	private LeagueModel league;
	private ConferenceModel conference;
	private DivisionModel division;
	private TeamModel team;
	public ConferenceModel getConference() {
		return conference;
	}

	public void setConference(ConferenceModel conference) {
		this.conference = conference;
	}

	public DivisionModel getDivision() {
		return division;
	}

	public void setDivision(DivisionModel division) {
		this.division = division;
	}

	public TeamModel getTeam() {
		return team;
	}

	public void setTeam(TeamModel team) {
		this.team = team;
	}

	private TradingModel trade;

	public Object getTradeAlgorithm() {
		return TradeAlgorithm;
	}

	public void setTradeAlgorithm(Object tradeAlgorithm) {
		TradeAlgorithm = tradeAlgorithm;
	}

	public LeagueModel getLeague() {
		return league;
	}

	public void setLeague(LeagueModel league) {
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

		for (ConferenceModel c: league.getConferenceDetails()) 
		{
			for (DivisionModel d: c.getDivisionDetails()) 
			{
				for (TeamModel t: d.getTeamDetails())
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
