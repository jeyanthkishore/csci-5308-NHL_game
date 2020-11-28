package com.dhl.g05.trading;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.freeagent.FreeAgentModel;
import com.dhl.g05.gameplayconfig.ITradingConfig;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.player.AbstractPlayerFactory;
import com.dhl.g05.player.IGenerateNewPlayers;
import com.dhl.g05.player.IPlayer;
import com.dhl.g05.player.PlayerFactory;
import com.dhl.g05.team.ITeam;

public class InitiateTradeOfferTest {
	
	 private static IIntiateTradeOffer intiateTradeOffer;
	   private static AbstractTradingFactory abstractTradingFactory;

	    @BeforeClass
	    public static void setup() {
	    	AbstractTradingFactory.setFactory(new TradingFactory());
	    	abstractTradingFactory = AbstractTradingFactory.getFactory();

	    }

//	@Test
//	public void TeamStrengthAfterTradeTest() {
//		ILeague league = initiateTradeOfferTest();
//		for (IConference c : league.getConferenceDetails()) {
//			for (IDivision d : c.getDivisionDetails()) {
//				for (ITeam t : d.getTeamDetails()) {
//					{
//						assertSame(t.getPlayerList().size(), 5);
//					}
//				}
//			}
//		}
//	}

//	@Test 
//	public void teamAfterTradeAcceptedTest()
//	{
//		ILeague league = initiateTradeOfferTest();
//		List<IPlayer> player=new ArrayList<>();
//		for (IConference c : league.getConferenceDetails()) {
//			for (IDivision d : c.getDivisionDetails()) {
//				for (ITeam t : d.getTeamDetails()) {
//					{
//						  player = t.getPlayerList();
//						  break;
//						}
//
//					}
//						}
//			}
//		
//		}
		
	

	public ILeague initiateTradeOfferTest() {
		MockLeagueModel mock = new MockLeagueModel();
		ILeague league = mock.leagueMock();
		ITradingConfig trade = mock.TradingConfigMock();
		IIntiateTradeOffer tradeOffer = new InitiateTradeOffer();
		tradeOffer.setTrade(trade);
		return tradeOffer.initiateTradeOffer(league);
	}

}
