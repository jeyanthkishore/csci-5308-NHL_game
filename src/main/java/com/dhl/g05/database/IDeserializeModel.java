package com.dhl.g05.database;

import com.dhl.g05.model.ILeague;

public interface IDeserializeModel {

	ILeague deserializeObjects(String name, ILeague leagueModel);
}
