package com.dhl.g05.database;

public enum SerialiseLeagueConstant {
	
	FilePath("src/test/java/com/dhl/g05/jsontestfiles/OutputModel.json");

	private String value; 

	SerialiseLeagueConstant(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
