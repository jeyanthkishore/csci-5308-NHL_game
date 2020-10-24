package com.dhl.g05.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DateStoredProcedure {
	Connection conn = null;
	DbConnection db = new DbConnection();
	
	public List<Integer> loadDate(int leagueId) {
		ResultSet rs;
		ArrayList<Integer> result = new ArrayList<>();
		
		try {
			
			conn = db.createNewDBconnection();
			String query = "{CALL loadDate(?)}";
			java.sql.CallableStatement stmt = conn.prepareCall(query);
			stmt.setInt(1, leagueId);
			
			rs = stmt.executeQuery();
			

			while(rs.next()) {
			
				result.add(rs.getInt("day"));
				result.add(rs.getInt("month"));
				result.add(rs.getInt("year"));
				result.add(rs.getInt("days_since_stat_increase_check"));
				result.add(rs.getInt("days_until_stat_increase_check"));

			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();

		} finally {
			
			db.closeDbConnection(conn);
		}
		
		return result;	
	}
	
	
	
	public void saveDate(int leagueId, int day, int month, int year, int daysSinceStatIncreaseCheck, int daysUntilStatIncreaseCheck) {
		
		try {
			
			conn = db.createNewDBconnection();
			String query = "{CALL saveDate(?,?,?,?,?,?)}";
			java.sql.CallableStatement stmt = conn.prepareCall(query);
			
			stmt.setInt(1, leagueId);
			stmt.setInt(2, day);
			stmt.setInt(3, month);
			stmt.setInt(4, year);
			stmt.setInt(5, daysSinceStatIncreaseCheck);
			stmt.setInt(6, daysUntilStatIncreaseCheck);
			
			stmt.executeQuery();
			
			
		}
		
		catch (SQLException e) {
			
			e.printStackTrace();

		} finally {
			
			db.closeDbConnection(conn);
		}
	}
}
