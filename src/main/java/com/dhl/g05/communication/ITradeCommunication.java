package com.dhl.g05.communication;
import java.util.List;

import com.dhl.g05.freeagent.FreeAgentModel;
import com.dhl.g05.player.PlayerModel;


public interface ITradeCommunication {
	public void sendTradeMessage(List<PlayerModel> playersOffered, List<PlayerModel> playersRequested);
	public int getTradeDecision();
	public String getResponse();
	public void sendMessage(String message);
	public void DropPlayerDetails(List<PlayerModel> players, int count);
	public void AddPlayerDetails(List<FreeAgentModel> freeAgents, int count);

}
