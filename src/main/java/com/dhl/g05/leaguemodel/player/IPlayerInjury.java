package com.dhl.g05.leaguemodel.player;

import com.dhl.g05.leaguemodel.gameplayconfig.IInjury;
import com.dhl.g05.leaguemodel.gameplayconfig.Injury;

public interface IPlayerInjury {

	boolean isInjured(IPlayerProgress playerProgress, PlayerModel playerModel , IInjury injury);
	
}
