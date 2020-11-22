package com.dhl.g05.player;

import com.dhl.g05.gameplayconfig.IAging;
import com.dhl.g05.league.ILeague;

public interface IPlayerBirthday {
	
	public void decreaseStatOnBirthday(ILeague League, IAging agingConfig);
	

}
