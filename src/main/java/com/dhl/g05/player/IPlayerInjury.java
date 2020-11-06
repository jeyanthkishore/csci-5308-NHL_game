package com.dhl.g05.player;

import com.dhl.g05.gameplayconfig.IInjury;

public interface IPlayerInjury {

	boolean isPlayerInjured(IPlayerInjured playerProgress, PlayerModel playerModel , IInjury injury);
	
}
