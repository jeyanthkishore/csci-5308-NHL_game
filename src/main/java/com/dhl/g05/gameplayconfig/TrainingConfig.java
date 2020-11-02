package com.dhl.g05.gameplayconfig;

public class TrainingConfig implements IGetTrainingDays{
	
	private int daysUntilStatIncreaseCheck;
	
	public int getDaysUntilStatIncreaseCheck() {
		return daysUntilStatIncreaseCheck;
	}
	public TrainingConfig(int days) {
		daysUntilStatIncreaseCheck = days;
	}
	
}	
