package com.dhl.g05.database;

import com.dhl.g05.model.ILeague;

public interface ISerializeModel {

	public Boolean serialiseObjects(ILeague object, String teamName);

}
