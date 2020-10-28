package com.dhl.g05.operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dhl.g05.db.StoredProcedure;
import com.dhl.g05.leaguemodel.coach.CoachObject;
import com.dhl.g05.leaguemodel.player.PlayerObject;
import com.dhl.g05.leaguemodel.team.ITeamModelPersistence;
import com.dhl.g05.leaguemodel.team.TeamObject;

public class TeamPersistence implements ITeamModelPersistence{
	
	private List<PlayerObject> playerList = new ArrayList<PlayerObject>();

	@Override
	public int saveTeamObject(int divisionId, TeamObject teamObject, CoachObject coachDetails) {
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
	public int loadTeamObject(int divisionId, TeamObject teamObject, CoachObject coachObject) {
		String teamName = teamObject.getTeamName();
		StoredProcedure sp= new StoredProcedure();
		int teamId = sp.getTeamID(teamName, divisionId);
		List<HashMap<String, Object>> playerValue = new ArrayList<HashMap<String,Object>>();
		playerValue = sp.fetchAllPlayers(teamId);
		for(HashMap<String, Object> player : playerValue) {
			String playerName = player.get("player_name").toString();
			double age = Double.parseDouble(player.get("age").toString());
			double skating = Double.parseDouble(player.get("skating").toString());
			double shooting = Double.parseDouble(player.get("shooting").toString());
			double checking = Double.parseDouble(player.get("checking").toString());
			double saving = Double.parseDouble(player.get("saving").toString());
			playerList.add(new PlayerObject(playerName,null,null,age,skating,shooting,checking,saving));
		}
		teamObject.setPlayerList(playerList);
		List<HashMap<String, Object>> coachValue = new ArrayList<HashMap<String,Object>>();
		coachValue = sp.fetchTeamCoach(teamId);
		for(HashMap<String, Object> coach : coachValue) {
			String name = coach.get("name").toString();
			double coachSkating = Double.parseDouble(coach.get("skating").toString());
			double coachShooting = Double.parseDouble(coach.get("shooting").toString());
			double coachChecking = Double.parseDouble(coach.get("checking").toString());
			double coachSaving = Double.parseDouble(coach.get("saving").toString());
			coachObject.setName(name);
			coachObject.setSkating(coachSkating);
			coachObject.setChecking(coachChecking);
			coachObject.setSaving(coachSaving);
		}
		return teamId;
	}

}
