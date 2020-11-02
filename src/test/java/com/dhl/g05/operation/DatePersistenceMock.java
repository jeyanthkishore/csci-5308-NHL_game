package com.dhl.g05.operation;

import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.simulation.Date;

public class DatePersistenceMock implements IDatePersistence{

	@Override
	public void loadDate(LeagueModel league, Date date) {
		if (league.getLeagueName().equalsIgnoreCase("HockeyLeague")){
			Date.getInstance().setMonth(1);
			Date.getInstance().setYear(1);
			Date.getInstance().setDaysSinceStatIncreaseCheck(1);
			Date.getInstance().setDaysUntilStatIncreaseCheck(1);
		}
	}

	@Override
	public void saveDate(LeagueModel league, Date date) {
		if (league.getLeagueName().equalsIgnoreCase("HockeyLeague")){

		}
	}

}
