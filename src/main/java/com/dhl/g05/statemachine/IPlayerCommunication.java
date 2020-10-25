package com.dhl.g05.statemachine;

import java.util.List;

import com.dhl.g05.leaguemodel.CoachObject;
import com.dhl.g05.leaguemodel.FreeAgentObject;

public interface IPlayerCommunication {
	public void sendMessage(String message);
	public String getResponse();
	public int getResponseNumber();
	public String getFile();
	public void sendMessage(List<FreeAgentObject> free);
	public void sendCoachMessage(List<CoachObject> coachList);
}
