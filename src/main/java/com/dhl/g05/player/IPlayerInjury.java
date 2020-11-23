package com.dhl.g05.player;

import com.dhl.g05.gameplayconfig.IInjury;

public interface IPlayerInjury {

	boolean checkPlayerInjury(IPlayerInjured playerInjured, PlayerModel player, IInjury injury);
	
}
