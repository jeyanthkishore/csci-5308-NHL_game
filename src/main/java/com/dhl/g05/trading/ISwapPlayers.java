package com.dhl.g05.trading;

import java.util.List;

import com.dhl.g05.model.IPlayer;
import com.dhl.g05.model.ITeam;

public interface ISwapPlayers {

	public void swapPlayers(ITeam weakTeam, ITeam strongTeam, List<IPlayer> weakestPlayersToTrade,
			List<IPlayer> strongestPlayersToTrade);
}
