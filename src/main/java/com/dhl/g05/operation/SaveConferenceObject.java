package com.dhl.g05.operation;

import java.util.List;

import com.dhl.g05.leaguemodel.ConferenceObject;
import com.dhl.g05.leaguemodel.DivisionObject;
import com.dhl.g05.leaguemodel.LeagueObject;

public class SaveConferenceObject {
	private LeagueObject leagueObject;
	private List<DivisionObject> divisionList;
	private List<ConferenceObject> conferenceList;
	
	public LeagueObject getLeagueObject() {
		return leagueObject;
	}

	public void setLeagueObject(LeagueObject leagueObject) {
		this.leagueObject = leagueObject;
	}

	public SaveConferenceObject(ISavePersistance object) {
		object.loadLeagueModel(this);
	}

	public SaveConferenceObject() {
		setLeagueObject(null);
	}

	public Boolean saveConferenceDetail() {
		conferenceList = leagueObject.getConferenceDetails();
		int confSize = conferenceList.size();
		for(int conItereate = 0;conItereate<confSize;conItereate++) {
			divisionList = conferenceList.get(conItereate).getDivisionDetails();
			int divSize = divisionList.size();
			for(int divIterate =0;divIterate<divSize;divIterate++) {
				//write code for the call procedure
			}
		}
		return true;
	}
}
