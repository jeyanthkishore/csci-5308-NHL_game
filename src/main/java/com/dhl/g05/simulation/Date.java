package com.dhl.g05.simulation;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Year;

import com.dhl.g05.leaguemodel.league.LeagueModel;
import com.dhl.g05.operation.IDatePersistence;

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

	public void saveDate(LeagueModel league, IDatePersistence db) {
		
	}

	public void loadDate(LeagueModel league, IDatePersistence db) {
		db.loadDate(league,this);
	}


	public boolean ShouldTrainingOccur(){
		return IsTrainingDeadlinePassed();
	}


	public boolean IsTrainingDeadlinePassed(){
		return (daysSinceStatIncreaseCheck > daysUntilStatIncreaseCheck);
	}

	public void progressDays(int numberOfDays) {
		currentDate = currentDate.plusDays(numberOfDays);
	}

	public int getDay() {
		return currentDate.getDayOfMonth();
	}

	public int getMonth() {
		return currentDate.getMonthValue();
	}

	public int getYear() {
		return currentDate.getYear();
	}


	public void setDay(int day) {
		currentDate = currentDate.withDayOfMonth(day);
	}

	public void setMonth(int month) {
		currentDate = currentDate.withMonth(month);
	}

	public void setYear(int year) {
		currentDate = currentDate.withYear(year);
	}

	public void setDaysUntilStatIncreaseCheck(int days) {
		daysUntilStatIncreaseCheck = days;
	}

	public int getDaysUntilStatIncreaseCheck() {
		return daysUntilStatIncreaseCheck;
	}


	public void setDaysSinceStatIncreaseCheck(int days) {
		daysSinceStatIncreaseCheck = days;
	}

	public int getDaysSinceStatIncreaseCheck() {
		return daysSinceStatIncreaseCheck;
	}


}
