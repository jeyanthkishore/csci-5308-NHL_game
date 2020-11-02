package com.dhl.g05.player;


import com.dhl.g05.gameplayconfig.IAging;
import com.dhl.g05.gameplayconfig.IInjury;

public interface IPlayerProgress {

    boolean isInjured(PlayerModel playerObject, IInjury injury);

    boolean isRetired(PlayerModel player, IAging aging);
}
