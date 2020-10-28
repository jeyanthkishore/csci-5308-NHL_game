package com.dhl.g05.statemachine;

import java.util.List;

import com.dhl.g05.leaguemodel.coach.CoachModel;
import com.dhl.g05.leaguemodel.freeagent.FreeAgentModel;

public interface IPlayerCommunication {
	public void sendMessage(String message);
	public String getResponse();
	public int getResponseNumber();
	public String getFile();
	public void sendMessage(List<FreeAgentModel> free);
	public void sendCoachMessage(List<CoachModel> coachList);
}
