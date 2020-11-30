package com.dhl.g05.simulation.statemachine;

import com.dhl.g05.communication.IPlayerCommunication;
import com.dhl.g05.simulation.statemachine.AbstractState;

public class MockAbstractState extends AbstractState{
	private IPlayerCommunication communication;
	private int response;
	
	public MockAbstractState(IPlayerCommunication PlayerCommunication) {
		this.communication = PlayerCommunication;
	}

	@Override
	public boolean enter() {
		response = communication.getResponseNumber();
		if(response  == 1) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean performStateTask() {
		if(response  == 1) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean exit() {
		if(response  == 1) {
			return true;
		}else {
			return false;
		}
	}

}
