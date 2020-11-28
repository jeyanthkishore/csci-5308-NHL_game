package com.dhl.g05.model;

public enum CoachConstant {

	Success("success"),
	CoachNameEmpty("Coach Name Should Not have Empty Value"),
	InvalidStateOfCoach("Invalid state of coach");

	private String value; 

	CoachConstant(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
