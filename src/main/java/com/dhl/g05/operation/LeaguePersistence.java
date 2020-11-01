package com.dhl.g05.operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dhl.g05.db.StoredProcedure;
import com.dhl.g05.leaguemodel.coach.CoachModel;
import com.dhl.g05.leaguemodel.conference.ConferenceModel;
import com.dhl.g05.leaguemodel.freeagent.FreeAgentModel;
import com.dhl.g05.leaguemodel.league.ILeagueModelPersistence;
import com.dhl.g05.leaguemodel.league.LeagueModel;

public class LeaguePersistence implements ILeagueModelPersistence{

	private List<ConferenceModel> conferenceList = new ArrayList<ConferenceModel>();
	private List<FreeAgentModel> freeAgent = new ArrayList<FreeAgentModel>();
	private List<CoachModel> freeCoaches = new ArrayList<CoachModel>();
	private List<String> freeManager = new ArrayList<String>();

	@Override
	public ArrayList<HashMap<String, Object>> loadDetails() {
		StoredProcedure sp= new StoredProcedure();
		ArrayList<HashMap<String,Object>> leagueValue = new ArrayList<HashMap<String,Object>>();
		leagueValue = sp.fetchAllLeagues();
		return leagueValue;
	}

	@Override
	public int saveLeagueObject(LeagueModel leagueObject) {
		StoredProcedure sp= new StoredProcedure();
		String leagueName = leagueObject.getLeagueName();
		int leagueId = sp.saveLeague(leagueName);
		if(leagueId == 0 ) {
			return 0;
		}
		for(String m : leagueObject.getManagerList()) {
			int managerId = sp.saveFreeManager(leagueId, m);
			if(managerId == 0) {
				return 0;
			}
		}
		return leagueId;
	}

	@Override
	public int loadLeagueObject(int league_id, LeagueModel league) {
		StoredProcedure sp= new StoredProcedure();
		String leagueName = sp.getLeagueName(league_id);
		List<HashMap<String, Object>> conferenceValue = new ArrayList<HashMap<String,Object>>();
		conferenceList = new ArrayList<ConferenceModel>();

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
