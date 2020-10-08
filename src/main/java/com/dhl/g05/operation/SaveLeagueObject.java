package com.dhl.g05.operation;

import java.util.List;

import com.dhl.g05.leaguemodel.ConferenceObject;
import com.dhl.g05.leaguemodel.LeagueObject;
import com.dhl.g05.leaguemodel.PlayerObject;

public class SaveLeagueObject {

	private LeagueObject leagueObject;
	private List<ConferenceObject> conferenceList;
	private List<PlayerObject> freeAgent;
	private String leagueName;
	
	public SaveLeagueObject(ISavePersistance object) {
		object.loadLeagueModel(this);
	}

	public SaveLeagueObject() {
		// TODO Auto-generated constructor stub
	}

	public LeagueObject getLeagueObject() {
		return leagueObject;
	}


	public void setLeagueObject(LeagueObject leagueObject) {
		this.leagueObject = leagueObject;
	}


	public Boolean saveLeagueDetail() {
		conferenceList = leagueObject.getConferenceDetails();
		freeAgent = leagueObject.getFreeAgent();
		leagueName = leagueObject.getLeagueName();
		if(!saveLeagueName()) {
			return false;
		}
		if(!saveConferenceName()) {
			return false;
		}
		if(!saveFreeAgent()) {
			return false;
		}
		return true;
	}
	
	public Boolean saveLeagueName() {
		// To Call the procedure to save the League Name
		return true;
	}
	public Boolean saveConferenceName() {
		int conferenceSize = conferenceList.size();
		Boolean execute = true;
		for(int iteration = 0; iteration<conferenceSize;iteration++) {
//			execute = function to call procedure
			if(!execute) {
				return false;
			}
		}
		return true;
	}
	public Boolean saveFreeAgent() {
		int agent = freeAgent.size();
		Boolean execute = true;
		for(int iteration = 0; iteration<agent;iteration++) {
			//execute = function to call procedure
			if(!execute) {
				return false;
			}
		}
		return true;
	}
}
