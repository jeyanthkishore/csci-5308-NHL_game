package com.dhl.g05.player;


import com.dhl.g05.freeagent.IFreeAgent;
import com.dhl.g05.gameplayconfig.IAging;
import com.dhl.g05.gameplayconfig.IInjury;
import com.dhl.g05.league.ILeague;

public interface IPlayerProgress {

    boolean isInjured(PlayerModel playerObject, IInjury injury);

    boolean isRetired(PlayerModel player, IAging aging);

    boolean handleFreeAgentRetirement(IFreeAgent freeAgent, ILeague league);
}
