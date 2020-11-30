package com.dhl.g05.simulation;

import com.dhl.g05.model.ILeague;

public interface ILeagueCreator {
	
	public ILeague createLeagueFromFile(String fileName);
	
}
