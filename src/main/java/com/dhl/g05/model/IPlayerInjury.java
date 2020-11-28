package com.dhl.g05.model;

import com.dhl.g05.simulation.IInjury;

public interface IPlayerInjury {

	boolean checkPlayerInjury(IPlayerInjured playerInjured, IPlayer player, IInjury injury);
	
}
