package com.dhl.g05.operation;

import java.util.ArrayList;
import java.util.HashMap;

import com.dhl.g05.leaguemodel.LeagueObject;

public class OperationModel {
	private String leagueName;
	private String conferenceName;
	private String divisionName;
	private LeagueObject leagueObject;
	private String result;
	private ArrayList<HashMap<String,Object>> leagueCheck;
	private ArrayList<HashMap<String,Object>> newTeam;
	

	public OperationModel() {
		leagueName = null;
		conferenceName = null;
		divisionName = null;
		leagueObject = null;
		result = null;
		leagueCheck = null;
	}
	public OperationModel(IDataBasePersistence object) {
		object.loadDetails(this);
	}
	public OperationModel(String league,IDataBasePersistence object) {
		this.leagueName = league;
		object.loadModel(this);
	}
	
	public OperationModel(LeagueObject league,IDataBasePersistence Object) {
		this.leagueObject = league;
		Object.saveModel(this);
	}
	
	public ArrayList<HashMap<String, Object>> getNewTeam() {
		return newTeam;
	}
	
	public void setNewTeam(ArrayList<HashMap<String, Object>> newTeam) {
		this.newTeam = newTeam;
	}
	
	public ArrayList<HashMap<String,Object>> getLeagueCheck() {
		return leagueCheck;
	}

	public void setLeagueCheck(ArrayList<HashMap<String,Object>> leagueCheck) {
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
