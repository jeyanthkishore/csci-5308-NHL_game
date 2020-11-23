package com.dhl.g05.trading;

import java.util.List;

import com.dhl.g05.player.IPlayer;

public interface ISortPlayerStrength {

	public List<IPlayer> sortByAscending(List<IPlayer> players);

	public List<IPlayer> sortByDescending(List<IPlayer> players);
}
