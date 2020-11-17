package com.dhl.g05.statemachine;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.communication.AbstractCommunicationFactory;
import com.dhl.g05.communication.CommunicationFactory;
import com.dhl.g05.db.AbstractDataBaseFactory;
import com.dhl.g05.filehandler.LeagueModelJson;
import com.dhl.g05.statemachine.mocks.MockPlayerCommunication;

import filehandler.DatabaseMockFactory;

public class StateMachineTest {
	private static AbstractStateMachineFactory stateMachineFactory;
	private static MockPlayerCommunication communication;


	@BeforeClass
	public static void setup() {
		AbstractCommunicationFactory.setFactory(new CommunicationFactory());
		AbstractDataBaseFactory.setFactory(new DatabaseMockFactory());
		AbstractStateMachineFactory.setFactory(
				new StateMachineFactory(
						AbstractCommunicationFactory.getFactory().getCommunication(),
						new LeagueModelJson()
						)
				);
		stateMachineFactory = AbstractStateMachineFactory.getFactory();
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
		AbstractState createState = new MockAbstractState(AbstractCommunicationFactory.getFactory().getCommunication());
		IStateMachine stateMachine = stateMachineFactory.getStateMachine(createState);
		communication.commandLineInput("1\n2");
        stateMachine.enterState();
        assertNull(stateMachine.getCurrentState());
	}

}
