package com.dhl.g05.league;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dhl.g05.coach.CoachPersistence;
import com.dhl.g05.coach.ICoach;
import com.dhl.g05.coach.ICoachLoad;
import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.conference.IConference;
import com.dhl.g05.db.StoredProcedure;
import com.dhl.g05.freeagent.FreeAgentPersistence;
import com.dhl.g05.freeagent.IFreeAgent;
import com.dhl.g05.freeagent.IFreeAgentLoad;

public class LeaguePersistence implements ILeagueModelPersistence{

	private List<IConference> conferenceList = new ArrayList<>();
	private List<IFreeAgent> freeAgent = new ArrayList<>();
	private List<ICoach> freeCoaches = new ArrayList<>();
	private List<String> freeManager = new ArrayList<String>();

	@Override
	public ArrayList<HashMap<String, Object>> loadDetails() {
		StoredProcedure sp= new StoredProcedure();
		ArrayList<HashMap<String,Object>> leagueValue = new ArrayList<HashMap<String,Object>>();
		leagueValue = sp.fetchAllLeagues();
		return leagueValue;
	}

	@Override
	public boolean saveLeagueObject(LeagueModel leagueObject) {
		StoredProcedure sp= new StoredProcedure();
		String leagueName = leagueObject.getLeagueName();
		int leagueId = sp.saveLeague(leagueName);
		if(leagueId == 0 ) {
		}
		for(String m : leagueObject.getManagerList()) {
			int managerId = sp.saveFreeManager(leagueId, m);
			if(managerId == 0) {
			}
		}
		return true;
	}

	@Override
	public int loadLeagueObject(int league_id, LeagueModel league) {
		StoredProcedure sp= new StoredProcedure();
		String leagueName = sp.getLeagueName(league_id);
		List<HashMap<String, Object>> conferenceValue = new ArrayList<HashMap<String,Object>>();
		conferenceList = new ArrayList<>();

		conferenceValue = sp.fetchAllConferences(league_id);
		for(HashMap<String, Object> con : conferenceValue) {
			String conferenceName = con.get("conference_name").toString();
			conferenceList.add(new ConferenceModel(conferenceName,null));
		}
		league.setConferenceDetails(conferenceList);
		IFreeAgentLoad freeLoad = new FreeAgentPersistence();
		freeAgent = freeLoad.loadFreeAgentObject(leagueName);
		league.setFreeAgent(freeAgent);
		ICoachLoad coachLoad = new CoachPersistence();
		freeCoaches = coachLoad.loadLeagueCoachObject(leagueName);
		league.setFreeCoach(freeCoaches);


		List<HashMap<String,Object>> managersValue = new ArrayList<HashMap<String,Object>>();
		managersValue = sp.fetchAllFreeManager(league_id);
		for(HashMap<String, Object> manager : managersValue) {
			String name = manager.get("name").toString();
			freeManager.add(name);
		}
		league.setManagerList(freeManager);

		league.setLeagueName(leagueName);
		return league_id;
	}

	@Override
	public int loadLeagueFromTeam(String teamName) {
		StoredProcedure sp= new StoredProcedure();
		int league_id = sp.getLeagueFromTeam(teamName);
		return league_id;
	}

}
