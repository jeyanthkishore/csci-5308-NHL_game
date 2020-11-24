package com.dhl.g05.player;
import com.dhl.g05.gameplayconfig.IInjury;

public interface IPlayerInjured {

    boolean checkPlayerInjury(IPlayer playerObject, IInjury injury);

}
