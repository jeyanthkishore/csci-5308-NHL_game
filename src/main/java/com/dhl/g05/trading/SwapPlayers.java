package com.dhl.g05.trading;

import java.util.List;

import com.dhl.g05.player.PlayerModel;
import com.dhl.g05.team.TeamModel;

public class SwapPlayers implements ISwapPlayers {

	public void swapPlayers(TeamModel weakTeam, TeamModel strongTeam, List<PlayerModel> weakestPlayersToTrade,
			List<PlayerModel> strongestPlayersToTrade) {
		List<PlayerModel> weakTeamPlayers = weakTeam.getPlayerList();
		List<PlayerModel> strongTeamPlayers = strongTeam.getPlayerList();
		List<PlayerModel> weakPlayersToTrade = weakestPlayersToTrade;
		List<PlayerModel> strongPlayersToTrade = strongestPlayersToTrade;

		weakTeamPlayers.addAll(strongPlayersToTrade);
		strongTeamPlayers.addAll(weakestPlayersToTrade);
		strongTeamPlayers.removeAll(strongestPlayersToTrade);
		weakTeamPlayers.removeAll(weakPlayersToTrade);
		weakTeam.setPlayerList(weakTeamPlayers);
		strongTeam.setPlayerList(strongTeamPlayers);

	}
}
