package com.dhl.g05.operation;

import java.util.List;

import com.dhl.g05.leaguemodel.ConferenceObject;
import com.dhl.g05.leaguemodel.DivisionObject;
import com.dhl.g05.leaguemodel.LeagueObject;
import com.dhl.g05.leaguemodel.TeamObject;

public class SaveTeamInformation {
	private LeagueObject leagueObject;
	private List<ConferenceObject> conferenceList;
	private List<DivisionObject> divisionList;
	private List<TeamObject> teamList;
	
	public SaveTeamInformation(ISavePersistance division) {
		division.loadLeagueModel(this);
	}
	
	public SaveTeamInformation() {
		setLeagueObject(null);
	}

	public LeagueObject getLeagueObject() {
		return leagueObject;
	}
	
	public void setLeagueObject(LeagueObject leagueObject) {
		this.leagueObject = leagueObject;
	}
	
	public Boolean saveTeamDetail() {
		conferenceList = leagueObject.getConferenceDetails();
		int confSize = conferenceList.size();
		for(int conItereate = 0;conItereate<confSize;conItereate++) {
			divisionList = conferenceList.get(conItereate).getDivisionDetails();
			int divSize = divisionList.size();
			for(int divIterate =0;divIterate<divSize;divIterate++) {
				teamList = divisionList.get(divIterate).getTeamDetails();
				int teamCount = teamList.size();
				for(int teamIndex = 0; teamIndex<teamCount;teamIndex++) {
					//
				}
			}
		}
		return true;
	}


}
