package com.dhl.g05.statemachine;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.communication.AbstractCommunicationFactory;
import com.dhl.g05.communication.CommunicationFactory;
import com.dhl.g05.db.AbstractDataBaseFactory;
import com.dhl.g05.filehandler.LeagueModelJson;
import com.dhl.g05.statemachine.mocks.MockPlayerCommunication;

import filehandler.DatabaseMockFactory;

public class LoadTeamStateTest {
	private AbstractState state;
	private static MockPlayerCommunication communicate;


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
	        communicate = new MockPlayerCommunication();
	    }
	
	@Before
	public void init() {
		state = AbstractStateMachineFactory.getFactory().getLoadTeamState();
	}

	
	@Test
	public void operationTest() {
		communicate.commandLineInput("Striker Six");
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof PlayerChoiceState);
	}
	
	@Test
	public void operationNoTeamTest() {
		communicate.commandLineInput("Striker");
		state.enter();
		assertFalse(state.performStateTask());
	}
	
//	@Test
//	public void testEnter() {
//		assertTrue(state.enter());
//		assertNotNull(state.getTeamName());
//	}
//
//	@Test
//	public void testPerformStateTaskSucceeds() {
//		assertTrue(state.performStateTask());
//		assertNotNull(state.getOuterStateMachine().getLeagueModel().getLeague());
//	}
//
//	@Test
//	public void testPerformStateTaskFails() {
//		state.getOuterStateMachine().setLeagueModel(new MockLeagueModelValidationFails());
//		state.setTeamName("");
//		assertFalse(state.performStateTask());
//		assertNull(state.getNextState());
//	}

	@Test
	public void testExit() {
		assertTrue(state.exit());
		assertTrue(state.getNextState() instanceof PlayerChoiceState);
	}

}
