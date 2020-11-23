package com.dhl.g05.trading;

import com.dhl.g05.team.ITeam;

public interface IResolveTrade {
	
	public void resolveTrade();

	public void dropToFreeAgentList(ITeam team, String defense, int i);

}
