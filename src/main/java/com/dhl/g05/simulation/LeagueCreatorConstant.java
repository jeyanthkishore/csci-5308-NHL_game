package com.dhl.g05.simulation;

public enum LeagueCreatorConstant {

	GameplayConfigNull("Null Value in the GamePlayConfig"),
	FileNotFound("File not found\n"),
	AgingConfigNull("Null Value in the AgingConfig"),
	InjuryConfigNull("Null Value in the InjuryConfig"),
	TrainingConfigNull("Null Value in the TrainingConfig"),
	GameResolverConfigNull("Null Value in the GameResolverConfig"), 
	TradingConfigNull("Null Value in the TradingConfig");

	private String value;

	LeagueCreatorConstant(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
