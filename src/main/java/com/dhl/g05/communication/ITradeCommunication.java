package com.dhl.g05.communication;

import java.util.List;
import com.dhl.g05.freeagent.IFreeAgent;
import com.dhl.g05.player.IPlayer;

public interface ITradeCommunication {
	
	public void sendTradeMessage(List<IPlayer> list, List<IPlayer> list2);

	public int getTradeDecision();

	public String getResponse();

	public void sendMessage(String message);

	public void DropPlayerDetails(List<IPlayer> players, int count);

	public void AddPlayerDetails(List<IFreeAgent> freeAgents, int count);

}
