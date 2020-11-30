package com.dhl.g05.database;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.ILeague;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SerialiseLeagueModel implements ISerializeModel{

	@Override
	public Boolean serialiseObjects(ILeague leagueObject, String teamName) {
		DatabaseAbstractFactory databaseFactory = ApplicationConfiguration.instance().getDatabaseConcreteFactoryState();
		IFileOperation fileValidator = databaseFactory.createFileOperation();
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
		String path = fileValidator.getFilePath(teamName);
		FileWriter myWriter;
		try {
			if(fileValidator.isFileExist(teamName)) {
				myWriter = new FileWriter(path);
			}else {
				File file = new File(path);
				myWriter = new FileWriter(file);
			}
			String object = gson.toJson(leagueObject);
			myWriter.write(object);
			myWriter.flush();
			myWriter.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);
			return false;
		}
	}

}
