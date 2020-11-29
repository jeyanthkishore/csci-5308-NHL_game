package com.dhl.g05.simulation;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.communication.CommunicationAbstractFactory;
import com.dhl.g05.communication.MockPlayerCommunication;

public class StateMachineTest {
	private static SimulationAbstractFactory stateMachineFactory;
	private static MockPlayerCommunication communication;


	@BeforeClass
	public static void setup() {
		stateMachineFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		communication = new MockPlayerCommunication();
	}


	@Test
	public void ConstructorTest() {
		AbstractState importState = stateMachineFactory.createImportState();
		IStateMachine stateMachine = stateMachineFactory.createStateMachine(importState);
		assertTrue(stateMachine.getCurrentState() instanceof ImportState);
	}

	@Test
	public void setCurrentStateTest() {
		AbstractState createTeamState = stateMachineFactory.createCreateTeamState();
		IStateMachine stateMachine = stateMachineFactory.createStateMachine(createTeamState);

		stateMachine.setCurrentState(createTeamState);
		assertTrue(stateMachine.getCurrentState() instanceof CreateTeamState);
	}
	
	@Test 
	public void taskStateTest() {
		CommunicationAbstractFactory communicationState = ApplicationConfiguration.instance().getCommunicationConcreteFactoryState();
		AbstractState createState = new MockAbstractState(communicationState.getCommunication());
		IStateMachine stateMachine = stateMachineFactory.createStateMachine(createState);
		communication.commandLineInput("1\n2");
        stateMachine.enterState();
        assertNull(stateMachine.getCurrentState());
	}

}
