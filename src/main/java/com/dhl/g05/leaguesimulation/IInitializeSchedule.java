package com.dhl.g05.leaguesimulation;

import com.dhl.g05.league.ILeague;

public interface IInitializeSchedule {
	
	void generateRegularSeason(ILeague league);

	void generatePlayOff(ILeague league, ILeagueStanding standingSystem);
}
