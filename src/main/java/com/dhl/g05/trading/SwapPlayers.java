package com.dhl.g05.trading;

import java.util.List;

import com.dhl.g05.model.IPlayer;
import com.dhl.g05.model.ITeam;

public class SwapPlayers implements ISwapPlayers {

	public void swapPlayers(ITeam weakTeam, ITeam strongTeam, List<IPlayer> weakestPlayersToTrade,List<IPlayer> strongestPlayersToTrade) {
		List<IPlayer> weakTeamPlayers = weakTeam.getPlayerList();
		List<IPlayer> strongTeamPlayers = strongTeam.getPlayerList();
		List<IPlayer> weakPlayersToTrade = weakestPlayersToTrade;
		List<IPlayer> strongPlayersToTrade = strongestPlayersToTrade;
		weakTeamPlayers.addAll(strongPlayersToTrade);
		strongTeamPlayers.addAll(weakestPlayersToTrade);
		strongTeamPlayers.removeAll(strongestPlayersToTrade);
		weakTeamPlayers.removeAll(weakPlayersToTrade);
		weakTeam.setPlayerList(weakTeamPlayers);
		strongTeam.setPlayerList(strongTeamPlayers);
	}
}
