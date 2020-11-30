package com.dhl.g05.simulation.statemachine;

import java.util.InputMismatchException;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.communication.IPlayerCommunication;
import com.dhl.g05.simulation.SimulationAbstractFactory;

public class PlayerChoiceState extends AbstractState{

	private IPlayerCommunication communication;
	private int choice;

	public PlayerChoiceState(IPlayerCommunication communicate) {
		this.communication = communicate;
	}

	@Override
	public boolean enter() {
		communication.sendMessage(StateMachineConstant.SeasonSimulate.getValue());
		return true;
	}

	@Override
	public boolean performStateTask() {
		try {
			this.choice = communication.getResponseNumber();
		}catch(InputMismatchException e) {
			communication.sendMessage(StateMachineConstant.NoNumberEntered.getValue());
			return false;
		}
		return true;
	}

	@Override
	public boolean exit() {
		SimulationAbstractFactory simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		this.setNextState(simulationFactory.createStimulateState(choice));
		return true;
	}
}
