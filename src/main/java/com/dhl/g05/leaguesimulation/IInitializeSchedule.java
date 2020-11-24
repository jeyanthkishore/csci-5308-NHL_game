package com.dhl.g05.leaguesimulation;

import com.dhl.g05.league.ILeague;
import com.dhl.g05.league.LeagueModel;

public interface IInitializeSchedule {
	
	void generateRegularSeason(ILeague league);

	void generatePlayOff(ILeague league, ILeagueStanding standingSystem);
}
