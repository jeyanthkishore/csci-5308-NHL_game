package com.dhl.g05.freeagent;

public enum PositionConstant {

	forward("forward"),
	defense("defense"),
	goalie("goalie");
	
	private String value; 

	PositionConstant(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
