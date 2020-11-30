package com.dhl.g05.simulation.statemachine;

public enum GamePlayConfigConstant {

	Success("success"),
	ErrorInConfig("Error in GamePlayConfig");
	
	private String value; 

	GamePlayConfigConstant(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}


}
