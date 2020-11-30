package com.dhl.g05.database;

import com.dhl.g05.model.ILeague;

public class SerializeLeagueModelMock implements ISerializeModel{

	@Override
	public Boolean serialiseObjects(ILeague object, String teamName) {
		if(teamName.equals("serializeLeague")) {
			return true;
		}
		return false;
	}

}
