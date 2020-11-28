package com.dhl.g05.model;

import com.dhl.g05.simulation.IAging;

public interface IPlayerRetired {

    boolean checkPlayerRetirement(IAging aging, IPlayer player);

    boolean isFreeAgentsRetired(ILeague league, IFreeAgent freeAgent);

    boolean isPlayerRetired(ILeague league, IPlayer player, ITeam team);

}
