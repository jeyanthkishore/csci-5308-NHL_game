package com.dhl.g05.leaguemodel.player;

import com.dhl.g05.leaguemodel.gameplayconfig.IAging;
import com.dhl.g05.leaguemodel.gameplayconfig.IInjury;

public interface IPlayerProgress {

    boolean isInjured(PlayerModel playerObject, IInjury injury);

    boolean isRetired(PlayerModel player, IAging aging);
}
