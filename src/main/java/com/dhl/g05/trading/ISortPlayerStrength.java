package com.dhl.g05.trading;

import java.util.List;

import com.dhl.g05.player.PlayerModel;

public interface ISortPlayerStrength {

	public List<PlayerModel> sortByAscending(List<PlayerModel> players);

	public List<PlayerModel> sortByDescending(List<PlayerModel> players);
}
