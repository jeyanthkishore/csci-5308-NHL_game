package com.dhl.g05.communication;

import java.util.List;

import com.dhl.g05.model.ICoach;
import com.dhl.g05.model.IFreeAgent;

public interface IPlayerCommunication {

	void sendMessage(String message);

	String getResponse();

	int getResponseNumber();

	String getFile();

	void displayFreeAgentList(List<IFreeAgent> freeAgentList);

	void displayCoachList(List<ICoach> coachList);

	void displayManagerList(List<String> managerList);

}
