package com.dhl.g05.simulation;

public enum TradingConstant {

	Success("success"),
	LossError("Error in Loss Points"), 
	OfferError("Invalid Offer Count in Config"), 
	MaxPlayerError("Invalid Max Player Per Trade in Config"), 
	AcceptanceError("Error in Random Acceptance of Config");
	
	private String value; 

	TradingConstant(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
