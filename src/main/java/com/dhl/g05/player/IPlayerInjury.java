package com.dhl.g05.player;

import com.dhl.g05.gameplayconfig.IInjury;

public interface IPlayerInjury {

	boolean isInjured(IPlayerProgress playerProgress, PlayerModel playerModel , IInjury injury);
	
}
