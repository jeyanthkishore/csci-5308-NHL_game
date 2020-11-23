package com.dhl.g05.player;

import com.dhl.g05.freeagent.IFreeAgent;
import com.dhl.g05.gameplayconfig.IAging;

public interface IPlayerRetired {

    boolean checkPlayerRetirement(IAging aging, IPlayer player);

}
