package com.dhl.g05.Training;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.dhl.g05.leaguemodel.league.LeagueModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SerialiseLeagueModel implements ISerializeModel{

	private LeagueModel leagueObject;

	public Boolean serialiseObjects(LeagueModel league) {
		this.leagueObject = league;
		GsonBuilder builder = new GsonBuilder(); 
		builder.setPrettyPrinting(); 
		Gson gson = builder.create();
		String serailizeObject = gson.toJson(leagueObject);
		System.out.println(serailizeObject);
		try {
			  File file = new File("src/test/java/com/dhl/g05/jsontestfiles/OutputModel.json");
		      FileWriter myWriter = new FileWriter(file);
		      myWriter.write(serailizeObject);
		      myWriter.close();
		      return true;
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		return false;
	}
}
