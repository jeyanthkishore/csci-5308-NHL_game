package com.dhl.g05.trading;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import com.dhl.g05.gamePlayConfig.TradingModel;
import com.dhl.g05.leaguemodel.league.LeagueObject;

public class InitiateTradeOfferTest{

	@Test
	public void generateTradeOfferTest() {
		LeagueModelTest mockLeague = new LeagueModelTest();
		LeagueObject league = mockLeague.leagueMock();

		MockTradingObject tradeMock= new MockTradingObject();
		
		TradingModel trade = tradeMock.TradingModelTest();
		
		InitiateTradeOffer startTrade= new InitiateTradeOffer();
		startTrade.setLeague(league);

		startTrade.setTrade(trade);
		
		ArrayList<String> expectedTeamName= new ArrayList <String>();
		expectedTeamName = startTrade.initiateTradeOffer();
		
		ArrayList<String> actualTeamName= new ArrayList <String>();
		actualTeamName.add("Tigers");
		actualTeamName.add("Rythm");
		actualTeamName.add("Hawkers");
		actualTeamName.add("Montreal");
		actualTeamName.add("Boston");
		assertTrue(actualTeamName.equals(expectedTeamName));

	}

	

}
