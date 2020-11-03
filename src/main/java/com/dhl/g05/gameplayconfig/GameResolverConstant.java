package com.dhl.g05.gameplayconfig;

public enum GameResolverConstant {

	Success("success"),
	RandWinError("Error in Random Win Chance");
	
	private String value; 

	GameResolverConstant(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
