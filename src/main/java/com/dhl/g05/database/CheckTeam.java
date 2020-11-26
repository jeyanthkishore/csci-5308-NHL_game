package com.dhl.g05.database;

import com.dhl.g05.ApplicationConfiguration;

public class CheckTeam implements ICheckTeam{

	@Override
	public Boolean isTeamExist(String name) {
		DatabaseAbstractFactory databaseFactory = ApplicationConfiguration.instance().getDatabaseFactoryState();
		IFileOperation fileCheck = databaseFactory.getFileDetails();
		return fileCheck.isFileExist(name);
	}

}
