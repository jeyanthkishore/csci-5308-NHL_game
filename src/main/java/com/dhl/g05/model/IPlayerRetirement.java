package com.dhl.g05.model;

import com.dhl.g05.simulation.IAging;

public interface IPlayerRetirement {

    boolean checkPlayerRetirement(IPlayerRetired playerRetired, IPlayer playerModel, IAging aging);

}

