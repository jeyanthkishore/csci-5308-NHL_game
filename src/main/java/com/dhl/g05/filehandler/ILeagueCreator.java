package com.dhl.g05.filehandler;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import com.dhl.g05.league.LeagueModel;

public interface ILeagueCreator {
	public LeagueModel createLeagueFromFile(String fileName) throws FileNotFoundException, IOException, ParseException ;
}
