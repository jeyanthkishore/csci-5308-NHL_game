package com.dhl.g05.database;

import com.dhl.g05.league.ILeague;

public class SerializeLeagueModelMock implements ISerializeModel{

	@Override
	public Boolean serialiseObjects(ILeague object, String teamName) {
		if(teamName.equals("TeamName")) {
			return true;
		}
		return false;
	}

}
