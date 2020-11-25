package com.dhl.g05.database;

public class CheckTeamMock implements ICheckTeam {

	@Override
	public Boolean isTeamExist(String name) {
		if(name.equals("TeamName")) {
			return true;
		}
		return false;
	}

}
