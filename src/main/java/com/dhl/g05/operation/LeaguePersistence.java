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
import com.dhl.g05.leaguemodel.manager.ManagerModel;

public class LeaguePersistence implements ILeagueModelPersistence{

	private List<ConferenceModel> conferenceList = new ArrayList<ConferenceModel>();
	private List<FreeAgentModel> freeAgent = new ArrayList<FreeAgentModel>();
	private List<CoachModel> freeCoaches = new ArrayList<CoachModel>();
	private List<ManagerModel> freeManager = new ArrayList<ManagerModel>();

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
		return leagueId;
	}

	@Override
	public int loadLeagueObject(String leagueName, LeagueModel league) {
		StoredProcedure sp= new StoredProcedure();
		int league_id = sp.getLeagueID(leagueName);
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
		IManagerLoad managerLoad = new ManagerPersistence();
		freeManager = managerLoad.loadLeagueManagerObject(leagueName);
		league.setManagerList(freeManager);
		league.setLeagueName(leagueName);
		return league_id;
	}

}
