package com.dhl.g05.communication;

import java.util.List;

import com.dhl.g05.model.ICoach;
import com.dhl.g05.model.IFreeAgent;

public interface IPlayerCommunication {

	public void sendMessage(String message);

	public String getResponse();

	public int getResponseNumber();

	public String getFile();

	public void sendMessage(List<IFreeAgent> free);

	public void sendCoachMessage(List<ICoach> coachList);

	public void sendManagerMessage(List<String> managerList);

}
