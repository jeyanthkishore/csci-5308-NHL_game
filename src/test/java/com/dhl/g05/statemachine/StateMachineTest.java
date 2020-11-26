package com.dhl.g05.statemachine;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.communication.CommunicationAbstractFactory;
import com.dhl.g05.communication.CommunicationFactory;
import com.dhl.g05.communication.MockPlayerCommunication;
import com.dhl.g05.database.DatabaseAbstractFactory;
import com.dhl.g05.database.DatabaseFactory;
import com.dhl.g05.filehandler.LeagueModelJson;

public class StateMachineTest {
	private static StateMachineAbstractFactory stateMachineFactory;
	private static MockPlayerCommunication communication;


	@BeforeClass
	public static void setup() {
		stateMachineFactory = ApplicationConfiguration.instance().getStateMachineFactoryState();
		communication = new MockPlayerCommunication();
	}


	@Test
	public void ConstructorTest() {
		AbstractState importState = stateMachineFactory.getImportState();
		IStateMachine stateMachine = stateMachineFactory.getStateMachine(importState);
		assertTrue(stateMachine.getCurrentState() instanceof ImportState);
	}

	@Test
	public void setCurrentStateTest() {
		AbstractState createTeamState = stateMachineFactory.getCreateTeamState();
		IStateMachine stateMachine = stateMachineFactory.getStateMachine(createTeamState);

		stateMachine.setCurrentState(createTeamState);
		assertTrue(stateMachine.getCurrentState() instanceof CreateTeamState);
	}
	
	@Test 
	public void taskStateTest() {
		CommunicationAbstractFactory communicationState = ApplicationConfiguration.instance().getCommunicationFactoryState();
		AbstractState createState = new MockAbstractState(communicationState.getCommunication());
		IStateMachine stateMachine = stateMachineFactory.getStateMachine(createState);
		communication.commandLineInput("1\n2");
        stateMachine.enterState();
        assertNull(stateMachine.getCurrentState());
	}

}
