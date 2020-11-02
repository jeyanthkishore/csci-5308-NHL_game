package com.dhl.g05.trading;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import com.dhl.g05.gameplayconfig.TradingModel;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.team.TeamModel;

public class InitiateTradeOfferTest {
	private ArrayList<TeamModel> expectedTeam;

	public ArrayList<TeamModel> getExpectedTeam() {
		return expectedTeam;
	}

	public void setExpectedTeam(ArrayList<TeamModel> expectedTeam) {
		this.expectedTeam = expectedTeam;
	}

	@Test
	public void generateTradeOfferTest() {
		LeagueModelTest mockLeague = new LeagueModelTest();
		LeagueModel league = mockLeague.leagueMock();

		MockTradingObject tradeMock = new MockTradingObject();
		TradingModel trade = tradeMock.TradingModelTest();
		InitiateTradeOffer startTrade = new InitiateTradeOffer();
		startTrade.setTrade(trade);
		league= startTrade.initiateTradeOffer(league);
		
	    assertNotNull(league);

	}

}
