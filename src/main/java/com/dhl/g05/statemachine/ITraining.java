package com.dhl.g05.statemachine;

public interface ITraining {

	public int getDaysUntilStatIncreaseCheck() ;
	void setDaysUntilStatIncreaseCheck(int days);
	public TrainingConstant Validate();
}
