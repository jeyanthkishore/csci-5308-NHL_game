package com.dhl.g05.model;

import com.dhl.g05.gameplayconfig.IInjury;

public interface IPlayerInjury {

	boolean checkPlayerInjury(IPlayerInjured playerInjured, IPlayer player, IInjury injury);
	
}
