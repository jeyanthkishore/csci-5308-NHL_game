package com.dhl.g05.simulation.statemachine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.annotations.Expose;

public class TrainingConfig implements ITraining {
	
	static final Logger logger = LogManager.getLogger(TrainingConfig.class);
	@Expose
	private int daysUntilStatIncreaseCheck;
	
	public int getDaysUntilStatIncreaseCheck() {
		return daysUntilStatIncreaseCheck;
	}

	public void setDaysUntilStatIncreaseCheck(int days) {
		this.daysUntilStatIncreaseCheck = days;
	}
	
	public TrainingConstant Validate() {
		logger.info("Validating training details");
		
		if(daysUntilStatIncreaseCheck > 365|| daysUntilStatIncreaseCheck < 0) {
			return TrainingConstant.TrainingDaysError;
		}
		return TrainingConstant.Success;
	}
}	
