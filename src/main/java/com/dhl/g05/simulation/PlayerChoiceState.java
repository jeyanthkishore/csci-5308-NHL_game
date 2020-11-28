package com.dhl.g05.simulation;

import java.util.InputMismatchException;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.communication.IPlayerCommunication;

public class PlayerChoiceState extends AbstractState{

	private IPlayerCommunication communication;
	private int choice;

	public PlayerChoiceState(IPlayerCommunication communicate) {
		this.communication = communicate;
	}

	@Override
	public boolean enter() {
		communication.sendMessage("Enter the number of Season to stimulate");
		return true;
	}

	@Override
	public boolean performStateTask() {
		try {
			this.choice = communication.getResponseNumber();
		}catch(InputMismatchException e) {
			communication.sendMessage("No Number Was Entered, Please Enter a Number..");
			return false;
		}
		return true;
	}

	@Override
	public boolean exit() {
		SimulationAbstractFactory stateFactory = ApplicationConfiguration.instance().getStateMachineConcreteFactoryState();
		this.setNextState(stateFactory.createStimulateState(choice));
		return true;
	}
}
