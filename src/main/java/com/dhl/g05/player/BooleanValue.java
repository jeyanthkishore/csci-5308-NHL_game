package com.dhl.g05.player;

public enum BooleanValue {

	True(true),
	False(false);
	
	private boolean value; 

	BooleanValue(boolean value) {
		this.value = value;
	}

	public boolean getValue() {
		return value;
	}
	

}
