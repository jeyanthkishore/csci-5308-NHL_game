package com.dhl.g05.simulation;

public class Date {
	private static Date instance;
	private int daysUntilStatIncreaseCheck;
	
	
	
	private Date() {
		
	}

	public static Date getInstance() {
		if (instance == null) {
			instance = new Date();
		} 
		return instance;
	}
	
	public void setDaysUntilStatIncreaseCheck(int days) {
		daysUntilStatIncreaseCheck = days;
	}
	
	public int getDaysUntilStatIncreaseCheck() {
		return daysUntilStatIncreaseCheck;
	}
	
}
