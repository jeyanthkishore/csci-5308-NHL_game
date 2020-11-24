package com.dhl.g05.leaguesimulation.leagueschedule;

import com.dhl.g05.league.ILeague;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.leaguesimulation.leaguestanding.ILeagueStanding;

public interface IInitializeSchedule {
	
	void generateRegularSeason(ILeague league);

	void generatePlayOff(ILeague league, ILeagueStanding standingSystem);
}
