package com.dhl.g05.database;

import java.io.File;
import java.util.Scanner;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.league.LeagueModel;
import com.google.gson.Gson;

public class DeserializeLeagueModel implements IDeserializeModel {

	@Override
	public ILeague deserializeObjects(String name, ILeague leagueModel) {
		Gson gson = new Gson();
		Scanner reader = null;
		DatabaseAbstractFactory databaseFactory = ApplicationConfiguration.instance().getDatabaseFactoryState();
		IFileOperation file = databaseFactory.getFileDetails();
		String path = file.getFilePath(name);
		StringBuilder leagueData = new StringBuilder();
		try {
			File leagueFile = new File(path);
			reader = new Scanner(leagueFile);
			while(reader.hasNextLine()) {
				leagueData = leagueData.append(reader.nextLine());
			}
			leagueModel = gson.fromJson(leagueData.toString(), LeagueModel.class);
			reader.close();
		} catch(Exception exception) {
            exception.printStackTrace();
        }
		return leagueModel;
	}

}
