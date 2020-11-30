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
		System.out.println(teamName);
		String path = fileValidator.getFilePath(teamName);
		System.out.println(path);
		FileWriter myWriter;
		try {
			if(fileValidator.isFileExist(teamName)) {
				System.out.println("File PRESENT");
				myWriter = new FileWriter(path);
			}else {
				System.out.println("NEW FILE");
				File file = new File(path);
				myWriter = new FileWriter(file);
			}
			System.out.println("CONVERTION");
			String object = gson.toJson(leagueObject);
			System.out.println("WRITING");
			myWriter.write(object);
			System.out.println("FLUSH");
			myWriter.flush();
			System.out.println("CLOSE");
			myWriter.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);
			return false;
		}
	}

}
