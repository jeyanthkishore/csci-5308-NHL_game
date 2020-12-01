package com.dhl.g05.database;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.ILeague;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SerialiseLeagueModel implements ISerializeModel{
	
	static final Logger logger = LogManager.getLogger(SerialiseLeagueModel.class);

	@Override
	public Boolean serialiseObjects(ILeague leagueObject, String teamName) {
		logger.info("Serializing league Objects");
		
		DatabaseAbstractFactory databaseFactory = ApplicationConfiguration.instance().getDatabaseConcreteFactoryState();
		IFileOperation fileValidator = databaseFactory.createFileOperation();
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
		String path = fileValidator.getFilePath(teamName);
		FileWriter myWriter;
		try {
			if(fileValidator.isFileExist(teamName)) {
				logger.info("Writing existing file");
				myWriter = new FileWriter(path);
			}else {
				logger.info("Creating a new file");
				File file = new File(path);
				myWriter = new FileWriter(file);
			}
			String object = gson.toJson(leagueObject);
			myWriter.write(object);
			myWriter.flush();
			myWriter.close();
			return true;
		} catch (IOException e) {
			logger.error("Error while writing file to database");
			e.printStackTrace();
			System.out.println(e);
			return false;
		}
	}

}
