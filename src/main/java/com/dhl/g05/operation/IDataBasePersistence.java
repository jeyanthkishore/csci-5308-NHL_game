package com.dhl.g05.operation;

import java.util.ArrayList;
import java.util.HashMap;

import com.dhl.g05.leaguemodel.ConferenceObject;
import com.dhl.g05.leaguemodel.DivisionObject;
import com.dhl.g05.leaguemodel.LeagueObject;
import com.dhl.g05.leaguemodel.PlayerObject;
import com.dhl.g05.leaguemodel.TeamObject;

public interface IDataBasePersistence {

	public int saveLeagueObject(LeagueObject leagueObject);

	public int saveConferenceObject(int leagueId, ConferenceObject conferenceObject);

	public int saveDivisionObject(int conferenceId, DivisionObject divisionObject);

	public int saveTeamObject(int divisionId, TeamObject teamObject);

	public int savePlayerObject(int teamId, PlayerObject playerObject);

	public int loadLeagueObject(String leagueName, LeagueObject leagueObject);

	public int loadConferenceObject(int leagueId, ConferenceObject conferenceObject);

	public int loadDivisionObject(int conferenceId, DivisionObject divisionObject);

	public int loadTeamObject(int divisionId, TeamObject teamObject);

	public int loadPlayerObject(int teamId, PlayerObject playerObject);

	public ArrayList<HashMap<String, Object>> loadDetails();

}
