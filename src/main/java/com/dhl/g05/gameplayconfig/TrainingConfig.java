package com.dhl.g05.gameplayconfig;

public class TrainingConfig implements ITraining {
	
	private int daysUntilStatIncreaseCheck;
	
	public int getDaysUntilStatIncreaseCheck() {
		return daysUntilStatIncreaseCheck;
	}
	public TrainingConfig(int days) {
		daysUntilStatIncreaseCheck = days;
	}
	
	public TrainingConstant Validate() {
		if(daysUntilStatIncreaseCheck > 365|| daysUntilStatIncreaseCheck < 0) {
			return TrainingConstant.TrainingDaysError;
		}
		return TrainingConstant.Success;
	}
}	
