package com.dhl.g05.leaguemodel.player;

import com.dhl.g05.leaguemodel.gameplayconfig.Aging;
import com.dhl.g05.leaguemodel.gameplayconfig.IAging;

public interface IPlayerRetirement {

    boolean isRetired(IPlayerProgress playerProgress, PlayerModel player, IAging aging);
}
