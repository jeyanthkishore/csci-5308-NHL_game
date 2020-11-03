package com.dhl.g05.trading;

import java.util.List;

import com.dhl.g05.player.PlayerModel;
import com.dhl.g05.team.TeamModel;

public interface ISwapPlayers {

	public void swapPlayers(TeamModel weakTeam, TeamModel strongTeam, List<PlayerModel> weakestPlayersToTrade,
			List<PlayerModel> strongestPlayersToTrade);
}
