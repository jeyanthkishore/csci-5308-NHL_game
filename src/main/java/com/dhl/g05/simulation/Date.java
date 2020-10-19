package com.dhl.g05.simulation;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Year;

import com.dhl.g05.leaguemodel.IDataBasePersistence;
import com.dhl.g05.leaguemodel.LeagueObject;

public class Date {
	private static Date instance;
	private int daysUntilStatIncreaseCheck;
	private int daysSinceStatIncreaseCheck;
	private static LocalDate currentDate;
	
	private Date() {
		Year year = Year.now();
		currentDate = year.atMonthDay(MonthDay.of(9, 30));
		daysSinceStatIncreaseCheck = 0;
	}

	public static Date getInstance() {
		if (instance == null) {
			instance = new Date();
		} 
		return instance;
	}
	
	public boolean saveDate(LeagueObject league, IDataBasePersistence db) {
		//current day/month/year, daysSinceStatIncreadCheck, days until
	//	db.saveDate(this);
		return false;
	}
	
	public boolean loadDate(LeagueObject league, IDataBasePersistence db) {
		//current date, daysSinceStatIncreadCheck
	//	db.loadDate(this);
		return false;
	}
	
	
	public boolean ShouldTrainingOccur(){
		return false;
	}
	
	
	public boolean IsTrainingDeadlinePassed(){
		return false;
	}

	public void progressDays(int numberOfDays) {
		
	}
	
	public void progressWeeks(int numberOfWeeks) {
		
	}
	
	public int getDay() {
		return -1;
	}
	
	public int getMonth() {
		return -1;
	}
	
	public int getYear() {
		return -1;
	}
	
	public LocalDate getDate() {
		return null;
	}
	
	public void setDay(int day) {
		currentDate = currentDate.withDayOfMonth(day);
	}
	
	public void setMonth(int month) {
		
	}
	
	public void setYear(int month) {
		
	}
	
	public void setDaysUntilStatIncreaseCheck(int days) {
		daysUntilStatIncreaseCheck = days;
	}
	
	public int getDaysUntilStatIncreaseCheck() {
		return daysUntilStatIncreaseCheck;
	}
	

	
}
