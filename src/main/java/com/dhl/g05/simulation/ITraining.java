package com.dhl.g05.simulation;

public interface ITraining {

	public int getDaysUntilStatIncreaseCheck() ;
	void setDaysUntilStatIncreaseCheck(int days);
	public TrainingConstant Validate();
}
