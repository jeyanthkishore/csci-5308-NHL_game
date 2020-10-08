package com.dhl.g05.operation;

import java.util.List;

import com.dhl.g05.leaguemodel.ConferenceObject;
import com.dhl.g05.leaguemodel.DivisionObject;
import com.dhl.g05.leaguemodel.LeagueObject;
import com.dhl.g05.leaguemodel.TeamObject;

public class SavePlayerInformation {
	
	private LeagueObject leagueObject;
	private List<ConferenceObject> conferenceList;
	private List<DivisionObject> divisionList;
	private List<TeamObject> teamList;
	

	public SavePlayerInformation(ISavePersistance object) {
		object.loadLeagueModel(this);
	}

	public SavePlayerInformation() {
		setLeagueObject(null);
	}

	public LeagueObject getLeagueObject() {
		return leagueObject;
	}

	public void setLeagueObject(LeagueObject leagueObject) {
		this.leagueObject = leagueObject;
	}

	public Boolean savePlayerDetail() {
		conferenceList = leagueObject.getConferenceDetails();
		int conferenceSize = conferenceList.size();
		for(int conferenceIndex = 0; conferenceIndex < conferenceSize; conferenceIndex++ ) {
			divisionList = conferenceList.get(conferenceIndex).getDivisionDetails();
			int divisionSize = divisionList.size();
			for(int divisionIndex = 0; divisionIndex < divisionSize; divisionIndex++) {
				teamList = divisionList.get(divisionIndex).getTeamDetails();
				int teamSize = teamList.size();
				for(int teamIndex = 0; teamIndex < teamSize; teamIndex++) {
					//
				}
			}
		}
			return true;
	}

}
