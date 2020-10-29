package com.dhl.g05.trading;

import com.dhl.g05.leaguemodel.team.TeamModel;

public interface IAcceptRejectTrade {

	public boolean TradeResult(TeamModel weakTeam, TeamModel strongTeam);
}
