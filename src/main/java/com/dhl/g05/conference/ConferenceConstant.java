package com.dhl.g05.conference;

public enum ConferenceConstant {

	Success("success"),
	Failure("fails"),
	ConferenceNameEmpty("Conference Name Is Empty"),
	DivisionListEmpty("Division List Is Empty"),
	NoEvenDivisionCount("Division Count Must Be Even");

	private String value; 

	ConferenceConstant(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
