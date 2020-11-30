package com.dhl.g05.simulation.statemachine;

public interface ITraining {

	public int getDaysUntilStatIncreaseCheck() ;
	
	void setDaysUntilStatIncreaseCheck(int days);
	
	public TrainingConstant Validate();
	
}
