package com.dhl.g05.Training;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.dhl.g05.leaguemodel.league.LeagueModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SerialiseLeagueModel implements ISerializeModel{

	private LeagueModel leagueObject;
	private String serailizeObject;

	public String getSerailizeObject() {
		return serailizeObject;
	}

	public void setSerailizeObject(String serailizeObject) {
		this.serailizeObject = serailizeObject;
	}

	public Boolean serialiseObjects(LeagueModel league) {
		this.leagueObject = league;
		GsonBuilder builder = new GsonBuilder(); 
		builder.setPrettyPrinting(); 
		Gson gson = builder.create();
		serailizeObject = gson.toJson(leagueObject);
		System.out.println(serailizeObject);
		try {
			File file = new File(SerialiseLeagueConstant.FilePath.getValue());
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
