package com.dhl.g05.operation;

import com.dhl.g05.leaguemodel.league.LeagueObject;
import com.dhl.g05.simulation.Date;

public interface IDatePersistence {

	void loadDate(LeagueObject league, Date date);

	void saveDate(LeagueObject league, Date date);
	
}
