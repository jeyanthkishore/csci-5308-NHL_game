package com.dhl.g05.trading;

import com.dhl.g05.team.TeamModel;

public interface IAcceptRejectTrade {

	public boolean TradeResult(TeamModel weakTeam, TeamModel strongTeam);
}
