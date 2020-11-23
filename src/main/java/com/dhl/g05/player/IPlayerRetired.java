package com.dhl.g05.player;

import com.dhl.g05.freeagent.IFreeAgent;
import com.dhl.g05.gameplayconfig.IAging;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.team.ITeam;

public interface IPlayerRetired {

    boolean checkPlayerRetirement(IAging aging, IFreeAgent player);

    boolean isFreeAgentsRetired(ILeague league, IFreeAgent freeAgent);

    boolean isPlayerRetired(ILeague league, IPlayer player, ITeam team);

}
