package com.dhl.g05.model;

import com.dhl.g05.simulation.statemachine.IAging;

public interface IPlayerRetired {

    boolean checkPlayerRetirement(IAging aging, IPlayer player);

}
