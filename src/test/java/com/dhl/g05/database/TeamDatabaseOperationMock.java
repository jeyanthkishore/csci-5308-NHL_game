package com.dhl.g05.database;

public class TeamDatabaseOperationMock implements ITeamDatabaseOperation {

	@Override
	public Boolean isTeamExist(String name) {
		if(name.equals("TeamName")) {
			return true;
		}
		return false;
	}

}
