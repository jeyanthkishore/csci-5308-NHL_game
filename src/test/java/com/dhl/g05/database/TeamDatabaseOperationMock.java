package com.dhl.g05.database;

public class TeamDatabaseOperationMock implements ITeamDatabaseOperation {

	@Override
	public Boolean isTeamExist(String name) {
		if(name.equals("serializeLeague")) {
			return true;
		}
		return false;
	}

}
