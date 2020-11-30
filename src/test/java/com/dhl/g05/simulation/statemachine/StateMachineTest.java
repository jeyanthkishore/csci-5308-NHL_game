package com.dhl.g05.simulation.statemachine;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.ApplicationTestConfiguration;
import com.dhl.g05.communication.CommunicationAbstractFactory;
import com.dhl.g05.communication.MockPlayerCommunication;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.dhl.g05.simulation.SimulationMockAbstractFactory;

public class StateMachineTest {
	private static SimulationAbstractFactory simulationFactory;
	private static MockPlayerCommunication communication;


	@BeforeClass
	public static void setup() {
		simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		communication = new MockPlayerCommunication();
	}


	@Test
	public void ConstructorTest() {
		AbstractState importState = simulationFactory.createImportState();
		IStateMachine stateMachine = simulationFactory.createStateMachine(importState);
		assertTrue(stateMachine.getCurrentState() instanceof ImportState);
	}

	@Test
	public void setCurrentStateTest() {
		AbstractState createTeamState = simulationFactory.createCreateTeamState();
		IStateMachine stateMachine = simulationFactory.createStateMachine(createTeamState);

		stateMachine.setCurrentState(createTeamState);
		assertTrue(stateMachine.getCurrentState() instanceof CreateTeamState);
	}
	
	@Test 
	public void taskStateTest() {
		SimulationMockAbstractFactory simulationMockFactory = ApplicationTestConfiguration.instance().getSimulationMockConcreteFactoryState(); 
		CommunicationAbstractFactory communicationState = ApplicationConfiguration.instance().getCommunicationConcreteFactoryState();
		AbstractState createState = simulationMockFactory.createMockAbstractState(communicationState.getCommunication());
		IStateMachine stateMachine = simulationFactory.createStateMachine(createState);
		communication.commandLineInput("1\n2");
        stateMachine.enterState();
        assertNull(stateMachine.getCurrentState());
	}

}
