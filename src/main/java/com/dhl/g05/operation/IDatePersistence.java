package com.dhl.g05.operation;

import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.simulation.Date;

public interface IDatePersistence {

	void loadDate(LeagueModel league, Date date);

	void saveDate(LeagueModel league, Date date);

}
