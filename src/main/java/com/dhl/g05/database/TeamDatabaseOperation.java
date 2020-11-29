package com.dhl.g05.database;

import com.dhl.g05.ApplicationConfiguration;

public class TeamDatabaseOperation implements ITeamDatabaseOperation{

	@Override
	public Boolean isTeamExist(String name) {
		DatabaseAbstractFactory databaseFactory = ApplicationConfiguration.instance().getDatabaseConcreteFactoryState();
		IFileOperation fileCheck = databaseFactory.createFileOperation();
		return fileCheck.isFileExist(name);
	}

}
