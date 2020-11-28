package com.dhl.g05.trading;

import java.util.Comparator;
import java.util.List;

import com.dhl.g05.model.IPlayer;

public class SortPlayerStrength implements ISortPlayerStrength {

	public List<IPlayer> sortByAscending(List<IPlayer> players) {
		players.sort(Comparator.comparing(IPlayer::getPlayerStrength));
		return players;
	}

	public List<IPlayer> sortByDescending(List<IPlayer> players) {
		players.sort(Comparator.comparing(IPlayer::getPlayerStrength).reversed());
		return players;
	}
}
