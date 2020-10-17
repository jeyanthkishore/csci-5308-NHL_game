package com.dhl.g05.simulation;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Year;

import com.dhl.g05.leaguemodel.IDataBasePersistence;

public class Date {
	private static Date instance;
	private int daysUntilStatIncreaseCheck;
	private int daysSinceStatIncreaseCheck;
	private static LocalDate currentDate;
	
	private Date() {
		Year year = Year.now();
		currentDate = year.atMonthDay(MonthDay.of(9, 30));
	}

	public static Date getInstance() {
		if (instance == null) {
			instance = new Date();
		} 
		return instance;
	}
	
	public boolean saveDate(IDataBasePersistence db) {
		return false;
	}
	
	public boolean loadDate(IDataBasePersistence db) {
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
	
	public int getDayOfMonth() {
		return -1;
	}
	
	public int getMonthOfYear() {
		return -1;
	}
	
	public int getYear() {
		return -1;
	}
	
	public LocalDate getDate() {
		return null;
	}
	
	public void setDaysUntilStatIncreaseCheck(int days) {
		daysUntilStatIncreaseCheck = days;
	}
	
	public int getDaysUntilStatIncreaseCheck() {
		return daysUntilStatIncreaseCheck;
	}
	

	
}
