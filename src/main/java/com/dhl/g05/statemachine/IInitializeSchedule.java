package com.dhl.g05.statemachine;

import com.dhl.g05.model.ILeague;

public interface IInitializeSchedule {
	
	void generateRegularSeason(ILeague league);

	void generatePlayOff(ILeague league, ILeagueStanding standingSystem);
}
