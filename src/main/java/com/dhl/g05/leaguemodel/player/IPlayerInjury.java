package com.dhl.g05.leaguemodel.player;

import com.dhl.g05.leaguemodel.gameplayconfig.IInjury;

public interface IPlayerInjury {

	boolean isInjured(IPlayerProgress playerProgress, PlayerModel playerModel , IInjury injury);
	
}
