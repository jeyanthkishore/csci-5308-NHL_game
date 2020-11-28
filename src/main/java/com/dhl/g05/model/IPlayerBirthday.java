package com.dhl.g05.model;

import com.dhl.g05.statemachine.IAging;

public interface IPlayerBirthday {
	
	public void decreaseStatOnBirthday(ILeague League, IAging agingConfig);
	

}
