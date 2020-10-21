package com.dhl.g05.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StoredProcedure {
	
	Connection conn = null;
	DbConnection db = new DbConnection();
	ResultSet rs;
	ConvertResultSetToHashMap rsToList = new ConvertResultSetToHashMap();
	ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
	
	public ArrayList<HashMap<String, Object>> fetchAllLeagues() 
	{
		try {
			conn = db.createNewDBconnection();
			String query = "{CALL fetchAllLeagues()}";
			java.sql.CallableStatement stmt = conn.prepareCall(query);
			rs = stmt.executeQuery();
			list = rsToList.resultSetToArrayList(rs);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			db.closeDbConnection(conn);
		}
		return list;
	}
	
	public ArrayList<HashMap<String, Object>> fetchAllConferences(int league_id) 
	{
		try {
			conn = db.createNewDBconnection();
			String query = "{CALL fetchAllConferences(?)}";
			java.sql.CallableStatement stmt = conn.prepareCall(query);
			stmt.setInt(1, league_id);
			rs = stmt.executeQuery();
			list = rsToList.resultSetToArrayList(rs);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			db.closeDbConnection(conn);
		}
		return list;
	}

	public ArrayList<HashMap<String, Object>> fetchAllDivisions(int conference_id) 
	{
		try {
			conn = db.createNewDBconnection();
			String query = "{CALL fetchAllDivisions(?)}";
			java.sql.CallableStatement stmt = conn.prepareCall(query);
			stmt.setInt(1, conference_id);
			rs = stmt.executeQuery();
			list = rsToList.resultSetToArrayList(rs);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		} 
		finally 
		{
			db.closeDbConnection(conn);
		}
		return list;
	}
	
	public ArrayList<HashMap<String, Object>> fetchAllTeams(int division_id) 
	{
		try {
			conn = db.createNewDBconnection();
			String query = "{CALL fetchAllTeams(?)}";
			java.sql.CallableStatement stmt = conn.prepareCall(query);
			stmt.setInt(1,division_id);
			rs = stmt.executeQuery();
			list = rsToList.resultSetToArrayList(rs);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			db.closeDbConnection(conn);
		}
		return list;
	}

	public ArrayList<HashMap<String, Object>> fetchManagerCoach(int team_id) 
	{
		try {
			conn = db.createNewDBconnection();
			String query = "{CALL fetchManagerCoach(?)}";
			java.sql.CallableStatement stmt = conn.prepareCall(query);
			stmt.setInt(1, team_id);
			rs = stmt.executeQuery();
			list = rsToList.resultSetToArrayList(rs);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			db.closeDbConnection(conn);
		}
		return list;
	}


	public ArrayList<HashMap<String, Object>> fetchAllPlayers(int team_id) {
		try {
			conn = db.createNewDBconnection();
			String query = "{CALL fetchAllPlayers(?)}";
			java.sql.CallableStatement stmt = conn.prepareCall(query);
			stmt.setInt(1, team_id);
			rs = stmt.executeQuery();
			list = rsToList.resultSetToArrayList(rs);
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.closeDbConnection(conn);
		}
		return list;
	}
	
	public ArrayList<HashMap<String, Object>> fetchAllFreeAgents(String league_name) 
	{
		try {
			conn = db.createNewDBconnection();
			String query = "{CALL fetchAllFreeAgents(?)}";
			java.sql.CallableStatement stmt = conn.prepareCall(query);
			stmt.setString(1, league_name);
			rs = stmt.executeQuery();
			list = rsToList.resultSetToArrayList(rs);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			db.closeDbConnection(conn);
		}

		return list;
	}
	public ArrayList<HashMap<String, Object>> getAllUserStateTeams() 
	{
		try {
			conn = db.createNewDBconnection();
			String query = "{CALL getAllUserStateTeams()}";
			java.sql.CallableStatement stmt = conn.prepareCall(query);
			rs = stmt.executeQuery();
			list = rsToList.resultSetToArrayList(rs);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			db.closeDbConnection(conn);
		}

		return list;
	}
	public ArrayList<HashMap<String, Object>> loadTeam(String team_name) 
	{
		try {
			conn = db.createNewDBconnection();
			String query = "{CALL loadTeam(?)}";
			java.sql.CallableStatement stmt = conn.prepareCall(query);
			stmt.setString(1, team_name);
			rs = stmt.executeQuery();
			list = rsToList.resultSetToArrayList(rs);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			db.closeDbConnection(conn);
		}

		return list;
	}
	public int loadTeamStateByUser(String team_name, int team_id, String division_name, int league_id , String conference_name)
	{
		int result=0;
		try {
			conn = db.createNewDBconnection();
			String query = "{CALL loadTeamStateByUser(?,?,?,?,?)}";
			java.sql.CallableStatement stmt = conn.prepareCall(query);
			stmt.setString(1, team_name);
			stmt.setInt(2, team_id);
			stmt.setString(3, division_name);
			stmt.setInt(4, league_id);
			stmt.setString(5, conference_name);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				result=rs.getInt("LAST_INSERT_ID()");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();

		} finally {
			db.closeDbConnection(conn);
		}
		return result;	
	}

	public int saveLeague(String league_name) 
	{
		int result=0;;
		try {
			conn = db.createNewDBconnection();
			String query = "{CALL saveLeague(?)}";
			java.sql.CallableStatement stmt = conn.prepareCall(query);
			stmt.setString(1, league_name);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				result=rs.getInt("LAST_INSERT_ID()");
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			db.closeDbConnection(conn);
		}
		return result;
	}

	public int savePlayer(int team_id,int position_id,String player_name , int player_is_captain, int age, int skating, int shooting, int checking, int saving)
	//public int savePlayer(int team_id,int position_id,String player_name , int player_is_captain)
	{
		int result=0;
		try {
			conn = db.createNewDBconnection();
			//String query = "{CALL savePlayer(?,?,?,?)}";
			String query = "{CALL savePlayer(?,?,?,?,?,?,?,?,?)}";
			java.sql.CallableStatement stmt = conn.prepareCall(query);
			stmt.setInt(1,team_id);
			stmt.setInt(2,position_id);
			stmt.setString(3,player_name);
			stmt.setInt(4,player_is_captain);
			stmt.setInt(5,age);
			stmt.setInt(6,skating);
			stmt.setInt(7,shooting);
			stmt.setInt(8,checking);
			stmt.setInt(9,saving);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				result=rs.getInt("LAST_INSERT_ID()");
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			db.closeDbConnection(conn);
		}
		return result;
	}
	
	public int saveTeam(String team_name , int manager_id , int division_id , int coach_id )
	{
		int result=0;
		try {
			conn = db.createNewDBconnection();
			String query = "{CALL saveTeam(?,?,?,?)}";
			java.sql.CallableStatement stmt = conn.prepareCall(query);
			stmt.setString(1,team_name);
			stmt.setInt(2,manager_id);
			stmt.setInt(3,division_id);
			stmt.setInt(4,coach_id);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				result=rs.getInt("LAST_INSERT_ID()");
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			db.closeDbConnection(conn);
		}
		return result;
	}

	public int saveConference(int league_id , String conference_name) 
	{
		int result=0;
		try {
			conn = db.createNewDBconnection();
			String query = "{CALL saveConference(?,?)}";
			java.sql.CallableStatement stmt = conn.prepareCall(query);
			stmt.setInt(1, league_id);
			stmt.setString(2, conference_name);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				result=rs.getInt("LAST_INSERT_ID()");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();

		} finally {
			db.closeDbConnection(conn);
		}
		return result;
	}
	
	public int saveDivision(String  division_name, int conference_id )
	{
		int result=0;
		try {
			conn = db.createNewDBconnection();
			String query = "{CALL saveDivision(?,?)}";
			java.sql.CallableStatement stmt = conn.prepareCall(query);
			stmt.setString(1, division_name);
			stmt.setInt(2, conference_id);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				result=rs.getInt("LAST_INSERT_ID()");
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();

		} finally 
		{
			db.closeDbConnection(conn);

		}
		return result;
	}

	public int saveFreeAgent(String agent_name, String position_name , String league_name, int age, int skating, int shooting, int checking, int saving)
	//public int saveFreeAgent(String agent_name, String position_name , String league_name)
	{
		int result=0;
		try {
			conn = db.createNewDBconnection();
			//String query = "{CALL saveFreeAgent(?,?,?)}";
			String query = "{CALL saveFreeAgent(?,?,?,?,?,?,?,?)}";
			java.sql.CallableStatement stmt = conn.prepareCall(query);
			stmt.setString(1, agent_name);
			stmt.setString(2, position_name);
			stmt.setString(3, league_name);
			stmt.setInt(4,age);
			stmt.setInt(5,skating);
			stmt.setInt(6,shooting);
			stmt.setInt(7,checking);
			stmt.setInt(8,saving);

			rs = stmt.executeQuery();
			while(rs.next())
			{
				result=rs.getInt("LAST_INSERT_ID()");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();

		} finally {
			db.closeDbConnection(conn);
		}
		return result;	
	}
	public int saveManager(String manager_name )
	{
		int result=0;
		try {
			conn = db.createNewDBconnection();
			String query = "{CALL saveManager(?)}";
			java.sql.CallableStatement stmt = conn.prepareCall(query);
			stmt.setString(1, manager_name);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				result=rs.getInt("LAST_INSERT_ID()");
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();

		} finally 
		{
			db.closeDbConnection(conn);

		}
		return result;
	}
	public int saveCoach(String coach_name )
	{
		int result=0;
		try {
			conn = db.createNewDBconnection();
			String query = "{CALL saveCoach(?)}";
			java.sql.CallableStatement stmt = conn.prepareCall(query);
			stmt.setString(1, coach_name);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				result=rs.getInt("LAST_INSERT_ID()");
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();

		} finally 
		{
			db.closeDbConnection(conn);

		}
		return result;
	}
	
	
	public int getDivisionID(String division_name , int conference_id)
	{
		int result=0;
		try {
			conn = db.createNewDBconnection();
			String query = "{CALL getDivisionID(?,?)}";
			java.sql.CallableStatement stmt = conn.prepareCall(query);
			stmt.setString(1, division_name);
			stmt.setInt(2, conference_id);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				result=rs.getInt("division_id");
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		} finally 
		{
			db.closeDbConnection(conn);
		}
		return result;
	}
	
	public int getLeagueID(String league_name)
	{
		int result=0;
		try {
			conn = db.createNewDBconnection();
			String query = "{CALL getLeagueID(?)}";
			java.sql.CallableStatement stmt = conn.prepareCall(query);
			stmt.setString(1, league_name);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				result=rs.getInt("league_id");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();

		} finally {
			db.closeDbConnection(conn);
		}
		return result;
	}
	
	public int getTeamID(String team_name, int division_id)
	{
		int result=0;
		try {
			conn = db.createNewDBconnection();
			String query = "{CALL getTeamID(?,?)}";
			java.sql.CallableStatement stmt = conn.prepareCall(query);
			stmt.setString(1, team_name);
			stmt.setInt(2, division_id);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				result=rs.getInt("team_id");
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();

		} 
		finally 
		{
			db.closeDbConnection(conn);
		}
		return result;
	}
	
	public int getPositionID(String position_name)
	{
		int result=0;
		try {
			conn = db.createNewDBconnection();
			String query = "{CALL getPositionID(?)}";
			java.sql.CallableStatement stmt = conn.prepareCall(query);
			stmt.setString(1, position_name);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				result=rs.getInt("position_id");
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();

		} 
		finally 
		{
			db.closeDbConnection(conn);
		}
		return result;
	}
	
	public int getConferenceID(String conference_name, int league_id)
	{
		int result=0;
		try {
			conn = db.createNewDBconnection();
			String query = "{CALL getConferenceID(?,?)}";
			java.sql.CallableStatement stmt = conn.prepareCall(query);
			stmt.setString(1, conference_name);
			stmt.setInt(2, league_id);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				result=rs.getInt("conference_id");
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();

		} finally 
		{
			db.closeDbConnection(conn);
		}
		return result;
	}
	public String getLeagueName(int league_id)
	{
		String result="";
		try {
			conn = db.createNewDBconnection();
			String query = "{CALL getLeagueName(?)}";
			java.sql.CallableStatement stmt = conn.prepareCall(query);
			stmt.setInt(1, league_id);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				result=rs.getString("league_name");
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();

		} finally 
		{
			db.closeDbConnection(conn);
		}
		return result;
	}

	public int getPlayerID(int teamId, String playerName) {
		int result=0;
		try {
			conn = db.createNewDBconnection();
			String query = "{CALL getPlayerID(?,?)}";
			java.sql.CallableStatement stmt = conn.prepareCall(query);
			stmt.setString(1, playerName);
			stmt.setInt(2, teamId);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				result=rs.getInt("player_id");
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();

		} finally 
		{
			db.closeDbConnection(conn);
		}
		return result;
	}

	public List<HashMap<String, Object>> fetchPlayerDetails(int playerId) {
		try {
			conn = db.createNewDBconnection();
			String query = "{CALL fetchPlayerdetails(?)}";
			java.sql.CallableStatement stmt = conn.prepareCall(query);
			stmt.setInt(1, playerId);
			rs = stmt.executeQuery();
			list = rsToList.resultSetToArrayList(rs);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			db.closeDbConnection(conn);
		}

		return list;
	}

}

