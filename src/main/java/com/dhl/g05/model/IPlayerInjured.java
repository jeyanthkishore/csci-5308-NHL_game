package com.dhl.g05.model;
import com.dhl.g05.simulation.IInjury;

public interface IPlayerInjured {

    boolean checkPlayerInjury(IPlayer playerObject, IInjury injury);

}
