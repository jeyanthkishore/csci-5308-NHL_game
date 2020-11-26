package com.dhl.g05.database;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.league.ILeague;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SerialiseLeagueModel implements ISerializeModel{

	@Override
	public Boolean serialiseObjects(ILeague leagueObject, String teamName) {
		DatabaseAbstractFactory databaseFactory = ApplicationConfiguration.instance().getDatabaseFactoryState();
		IFileOperation fileValidator = databaseFactory.getFileDetails();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String path = fileValidator.getFilePath(teamName);
		FileWriter myWriter;
		try {
			if(fileValidator.isFileExist(teamName)) {
				myWriter = new FileWriter(path);
				String object = gson.toJson(leagueObject);
				myWriter.write(object);
			}else {
				File file = new File(path);
				myWriter = new FileWriter(file);
				String object = gson.toJson(leagueObject);
				myWriter.write(object);
			}
			myWriter.flush();
			myWriter.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

}
