package com.dhl.g05.trading;

import java.util.Comparator;
import java.util.List;

import com.dhl.g05.player.PlayerModel;

public class SortPlayerStrength implements ISortPlayerStrength {

	public List<PlayerModel> sortByAscending(List<PlayerModel> players) {
		players.sort(Comparator.comparing(PlayerModel::getPlayerStrength));
		return players;
	}

	public List<PlayerModel> sortByDescending(List<PlayerModel> players) {
		players.sort(Comparator.comparing(PlayerModel::getPlayerStrength).reversed());
		return players;
	}
}
