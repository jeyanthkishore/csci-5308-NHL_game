package com.dhl.g05.operation;

import com.dhl.g05.leaguemodel.LeagueObject;

public class SaveModel {

	public SaveModel() {
	}
	
	public String saveLeague() {
		if(!saveLeagueInformation()) {
			return "League failed";
		}else if(!saveConferenceInforamtion()) {
			return "Conference Save Failed";
		}else if(!saveTeamInformation()) {
			return "Division Save Failed";
		}else if(!savePlayerInformation()) {
			return "player Save Failed";
		}
		return "success";
	}
	public Boolean saveLeagueInformation() {
		ISavePersistance object = (ISavePersistance) new LeagueObject(); 
		SaveLeagueObject league = new SaveLeagueObject(object);
		Boolean result = league.saveLeagueDetail();
		return result;
	}
	public Boolean saveConferenceInforamtion() {
		ISavePersistance object = (ISavePersistance) new LeagueObject(); 
		SaveConferenceObject conference = new SaveConferenceObject(object);
		Boolean result = conference.saveConferenceDetail();
		return result;
	}
	public boolean saveTeamInformation() {
		ISavePersistance object = (ISavePersistance) new LeagueObject(); 
		SaveTeamInformation team = new SaveTeamInformation(object);
		Boolean result = team.saveTeamDetail();
		return result;
	}
	public boolean savePlayerInformation() {
		ISavePersistance object = (ISavePersistance) new LeagueObject(); 
		SavePlayerInformation conference = new SavePlayerInformation(object);
		Boolean result = conference.savePlayerDetail();
		return result;
	}


}
