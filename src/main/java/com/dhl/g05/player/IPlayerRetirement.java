package com.dhl.g05.player;

import com.dhl.g05.gameplayconfig.IAging;

public interface IPlayerRetirement {

    boolean checkPlayerRetirement(IPlayerRetired playerRetired, PlayerModel playerModel, IAging aging);

}

