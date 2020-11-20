package com.dhl.g05.statemachine;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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

public class ImportStateTest {
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
		state = AbstractStateMachineFactory.getFactory().getImportState();
	}

	@Test
	public void testPerformStateTask() {
		communicate.commandLineInput("src/test/java/com/dhl/g05/jsontestfiles/jsonGoodInfo.json");
		state.enter();
		state.performStateTask();
		state.exit();
		assertNotNull(state.getLeague());;
		assertTrue(state.getNextState() instanceof CreateTeamState);
	}

//	@Test
//	public void testPerformStateFailTask() {
//		communicate.commandLineInput("src/test/java/com/dhl/g05/jsontestfiles/jsonInvalidInfo.json");
//		state.enter();
//		state.performStateTask();
//		state.exit();
//		assertNotNull(state);
//		assertNull(state.getLeague());
//	}
	
//	@Test
//	public void testPerformLeagueNullTask() {
//		communicate.commandLineInput("src/test/java/com/dhl/g05/jsontestfiles/jsonBadConferenceInfo.json");
//		state.enter();
//		state.performStateTask();
//		state.exit();
//		assertNotNull(state);
//		assertNull(state.getLeague());
//	}
	
	
	@Test
	public void testExitNoFile() {
		communicate.commandLineInput("\r\n");
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof LoadTeamState);
	}

}
