package com.dhl.g05.operation;

import java.util.ArrayList;
import java.util.HashMap;

import com.dhl.g05.leaguemodel.*;
import com.dhl.g05.simulation.Date;

public interface IDataBasePersistence {

	public int saveLeagueObject(LeagueObject leagueObject);

	public int saveConferenceObject(int leagueId, ConferenceObject conferenceObject);

	public int saveDivisionObject(int conferenceId, DivisionObject divisionObject);

	public int saveTeamObject(int divisionId, TeamObject teamObject, CoachObject coachObject);

	public int savePlayerObject(int teamId, PlayerObject playerObject);

	public int saveLeagueCoachObject(int league_id, LeagueObject leagueObject);

	public int saveLeagueManagerObject(int league_id, ManagerObject managerObject);

	public int loadLeagueObject(String leagueName, LeagueObject leagueObject);

	public int loadLeagueCoachObject(String leagueName, LeagueObject league);

	public int loadLeagueManagerObject(String leagueName, ManagerObject managerObject);

	public int loadConferenceObject(int leagueId, ConferenceObject conferenceObject);

	public int loadDivisionObject(int conferenceId, DivisionObject divisionObject);

	public int loadTeamObject(int divisionId, TeamObject teamObject, CoachObject coachObject);

	public int loadPlayerObject(int teamId, PlayerObject playerObject);

	public ArrayList<HashMap<String, Object>> loadDetails();

	void loadDate(LeagueObject league, Date date);

	void saveDate(LeagueObject league, Date date);

}
