package com.dhl.g05.trading;

import java.util.List;

import com.dhl.g05.leaguemodel.player.PlayerModel;
import com.dhl.g05.leaguemodel.team.TeamModel;

public interface ISwapPlayers {

	public void swapPlayers(TeamModel weakTeam, TeamModel strongTeam, List<PlayerModel> weakestPlayersToTrade,
			List<PlayerModel> strongestPlayersToTrade);
}
