package com.dhl.g05.operation;

import java.util.ArrayList;
import java.util.HashMap;

import com.dhl.g05.leaguemodel.LeagueObject;

public class OperationModel {
	private String leagueName;
	private String conferenceName;
	private String divisionName;
	private String teamName;
	private LeagueObject leagueObject;
	private String result;
	private boolean leagueCheck;
	private ArrayList<HashMap<String,Object>> newTeam;
	
	public ArrayList<HashMap<String, Object>> getNewTeam() {
		return newTeam;
	}

	public void setNewTeam(ArrayList<HashMap<String, Object>> newTeam) {
		this.newTeam = newTeam;
	}

	public OperationModel() {
		leagueName = null;
		conferenceName = null;
		divisionName = null;
		teamName = null;
		leagueObject = null;
		result = null;
		leagueCheck = false;
	}
	
	public OperationModel(String league,String conference,String division,String team,IDataBasePersistence object) {
		this.leagueName = league;
		this.conferenceName = conference;
		this.divisionName = division;
		this.teamName = team;
		object.loadModel(this);
	}
	
	public OperationModel(LeagueObject league,IDataBasePersistence Object) {
		this.leagueObject = league;
		Object.saveModel(this);
	}
	
	public OperationModel(String league,IDataBasePersistence Object) {
		this.leagueName = league;
		leagueCheck = Object.checkLeagueExistence(this);
	}
	
	public boolean isLeagueCheck() {
		return leagueCheck;
	}

	public void setLeagueCheck(boolean leagueCheck) {
		this.leagueCheck = leagueCheck;
	}

	public String getLeagueName() {
		return leagueName;
	}
	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}
	public String getConferenceName() {
		return conferenceName;
	}
	public void setConferenceName(String conferenceName) {
		this.conferenceName = conferenceName;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public LeagueObject getLeagueObject() {
		return leagueObject;
	}
	public void setLeagueObject(LeagueObject leagueObject) {
		this.leagueObject = leagueObject;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
}
