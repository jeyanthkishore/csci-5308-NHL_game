package com.dhl.g05.operation;

import com.dhl.g05.leaguemodel.LeagueObject;
import com.dhl.g05.simulation.Date;

public interface IDatePersistence {
	public void saveDate(LeagueObject league, Date date);
	public void loadDate(LeagueObject league, Date date);
}
