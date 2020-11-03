package com.dhl.g05.player;

import com.dhl.g05.gameplayconfig.IAging;

public interface IPlayerRetirement {

    boolean isRetired(IPlayerProgress playerProgress, PlayerModel player, IAging aging);

}
