package com.dhl.g05.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dhl.g05.ApplicationConfiguration;

public class TeamDatabaseOperation implements ITeamDatabaseOperation{
	
	static final Logger logger = LogManager.getLogger(TeamDatabaseOperation.class);

	@Override
	public Boolean isTeamExist(String name) {
		logger.info("Check Team Exist in database or not");
		DatabaseAbstractFactory databaseFactory = ApplicationConfiguration.instance().getDatabaseConcreteFactoryState();
		IFileOperation fileCheck = databaseFactory.createFileOperation();
		return fileCheck.isFileExist(name);
	}

}
