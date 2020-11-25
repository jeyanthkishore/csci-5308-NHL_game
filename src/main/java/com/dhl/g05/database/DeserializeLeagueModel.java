package com.dhl.g05.database;

import java.io.File;
import java.util.Scanner;

import com.dhl.g05.league.ILeague;
import com.dhl.g05.league.LeagueModel;
import com.google.gson.Gson;

public class DeserializeLeagueModel implements IDeserializeModel {

	@Override
	public ILeague deserializeObjects(String name, ILeague leagueModel) {
		Gson gson = new Gson();
		Scanner reader = null;
		IFileOperation file = DataBaseFactory.getFactory().getFileDetails();
		String path = file.getFilePath(name);
		String leagueData = "";
		try {
			File leagueFile = new File(path);
			reader = new Scanner(leagueFile);
			while(reader.hasNextLine()) {
				leagueData = reader.nextLine();
			}
			leagueModel = gson.fromJson(leagueData, LeagueModel.class);
			reader.close();
		} catch(Exception exception) {
            exception.printStackTrace();
        }
		return leagueModel;
	}

}
