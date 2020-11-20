package com.dhl.g05.team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;

import com.dhl.g05.coach.CoachModel;
import com.dhl.g05.db.StoredProcedure;
import com.dhl.g05.player.PlayerModel;

public class TeamPersistence implements ITeamModelPersistence{

	@Override
	public int saveTeamObject(int divisionId, TeamModel teamObject, CoachModel coachDetails) {
		StoredProcedure sp= new StoredProcedure();
		String teamName = teamObject.getTeamName();
		String managerName = teamObject.getGeneralManagerName();
		String coachName = coachDetails.getName();
		double skating = coachDetails.getSkating();
		double shooting = coachDetails.getShooting();
		double checking = coachDetails.getChecking();
		double saving = coachDetails.getSaving();
		int managerId = sp.saveManager(managerName);
		int coachId = sp.saveCoach(coachName,skating,shooting,checking,saving);
		int teamId = sp.saveTeam(teamName,managerId,divisionId,coachId);
		return teamId;
	}

	@Override
	public int loadTeamObject(int divisionId, TeamModel teamObject, CoachModel coachObject) {
		String teamName = teamObject.getTeamName();
		StoredProcedure sp= new StoredProcedure();
		int teamId = sp.getTeamID(teamName, divisionId);
		List<HashMap<String, Object>> playerValue = new ArrayList<HashMap<String,Object>>();
		List<PlayerModel> playerList = new ArrayList<PlayerModel>();

		playerValue = sp.fetchAllPlayers(teamId);
		for(HashMap<String, Object> player : playerValue) {
			String playerName = player.get("player_name").toString();
			double skating = Double.parseDouble(player.get("skating").toString());
			double shooting = Double.parseDouble(player.get("shooting").toString());
			double checking = Double.parseDouble(player.get("checking").toString());
			double saving = Double.parseDouble(player.get("saving").toString());
			int birthDay=Integer.parseInt(player.get("birthDay").toString());
			int birthMonth=Integer.parseInt(player.get("birthMonth").toString());
			int birthYear=Integer.parseInt(player.get("birthYear").toString());
			playerList.add(new PlayerModel(playerName,null,null,skating,shooting,checking,saving, birthDay,birthMonth,birthYear));
		}

		teamObject.setPlayerList(playerList);
		List<HashMap<String, Object>> coachValue = new ArrayList<HashMap<String,Object>>();
		coachValue = sp.fetchTeamCoach(teamId);
		for(HashMap<String, Object> coach : coachValue) {
			String name = coach.get("coach_name").toString();
			double coachSkating = Double.parseDouble(coach.get("skating").toString());
			double coachShooting = Double.parseDouble(coach.get("shooting").toString());
			double coachChecking = Double.parseDouble(coach.get("checking").toString());
			double coachSaving = Double.parseDouble(coach.get("saving").toString());
			coachObject.setName(name);
			coachObject.setSkating(coachSkating);
			coachObject.setShooting(coachShooting);
			coachObject.setChecking(coachChecking);
			coachObject.setSaving(coachSaving);
		}
		return teamId;
	}

	@Override
	public List<HashMap<String, Object>> loadAllTeamName() {
		StoredProcedure sp= new StoredProcedure();
		List<HashMap<String, Object>> teamNames = new ArrayList<HashMap<String,Object>>();
		teamNames = sp.fetchAllTeamName();
		return teamNames;
	}

}
