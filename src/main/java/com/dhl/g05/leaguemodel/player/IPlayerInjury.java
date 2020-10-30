package com.dhl.g05.leaguemodel.player;

import com.dhl.g05.leaguemodel.gameplayconfig.Injury;

public interface IPlayerInjury {

	public boolean checkPlayerInjury(PlayerModel playerObject, Injury injury);
	
}
