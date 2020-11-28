package com.dhl.g05.statemachine;

public enum TrainingConstant {

	Success("success"),
	TrainingDaysError("Error in Training Days");
	
	private String value; 

	TrainingConstant(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
