package com.dhl.g05.trading;

import com.dhl.g05.leaguemodel.team.TeamModel;

public interface ITradeDecision {

	public boolean TradeResult(TeamModel weakTeam, TeamModel strongTeam);
}
