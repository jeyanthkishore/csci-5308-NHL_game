package com.dhl.g05.trading;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.dhl.g05.leaguemodel.team.TeamModel;

public class AcceptRejectTradeTest {
	
	@Test
	public void TradeResultTest()
	{
		AcceptRejectTrade acceptReject = new AcceptRejectTrade();
		TeamModel team1 = new TeamModel();
		team1.setTeamName("Tigers");
		team1.setUserTeam(true);
		

		TeamModel team2 = new TeamModel();
		team2.setTeamName("Rythm");
		team1.setUserTeam(false);
		boolean result= acceptReject.TradeResult(team1,team2);
		assertNotNull(result);
		
	}

}
