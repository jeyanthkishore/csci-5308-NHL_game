package com.dhl.g05.operation;

import java.util.List;

import com.dhl.g05.db.DateStoredProcedure;
import com.dhl.g05.db.StoredProcedure;
import com.dhl.g05.leaguemodel.league.LeagueObject;
import com.dhl.g05.simulation.Date;

public class DatePersistence implements IDatePersistence{

	@Override
	public void loadDate(LeagueObject league, Date date) {
		
		StoredProcedure sp = new StoredProcedure();
		int leagueID = sp.getLeagueID(league.getLeagueName());
		DateStoredProcedure dsp = new DateStoredProcedure();
		List<Integer> result = dsp.loadDate(leagueID);
		
		date.setDay(result.get(0));
		date.setMonth(result.get(1));
		date.setYear(result.get(2));
		date.setDaysSinceStatIncreaseCheck(result.get(3));
		date.setDaysUntilStatIncreaseCheck(result.get(4));
	
	}

	@Override
	public void saveDate(LeagueObject league, Date date) {
		StoredProcedure sp = new StoredProcedure();
		int leagueID = sp.getLeagueID(league.getLeagueName());
		int day = date.getDay();
		int month = date.getMonth();
		int year = date.getYear();
		int daysSinceStatIncreaseCheck = date.getDaysSinceStatIncreaseCheck();
		int daysUntilStatIncreaseCheck = date.getDaysUntilStatIncreaseCheck();
		
		DateStoredProcedure dsp = new DateStoredProcedure();
		dsp.saveDate(leagueID,day,month,year,daysSinceStatIncreaseCheck,daysUntilStatIncreaseCheck);
	}

}
