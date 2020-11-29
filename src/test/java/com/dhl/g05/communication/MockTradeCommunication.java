package com.dhl.g05.communication;

import java.util.List;

import com.dhl.g05.model.IFreeAgent;
import com.dhl.g05.model.IPlayer;

public class MockTradeCommunication implements ITradeCommunication {

	@Override
	public void sendTradeMessage(List<IPlayer> list, List<IPlayer> list2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTradeDecision() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getResponse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendMessage(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DropPlayerDetails(List<IPlayer> players, int count) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AddPlayerDetails(List<IFreeAgent> freeAgents, int count) {
		// TODO Auto-generated method stub
		
	}

}
