package com.dhl.g05.gameplayconfig;

public interface ITraining {

	public int getDaysUntilStatIncreaseCheck() ;
	void setDaysUntilStatIncreaseCheck(int days);
	public TrainingConstant Validate();
}
