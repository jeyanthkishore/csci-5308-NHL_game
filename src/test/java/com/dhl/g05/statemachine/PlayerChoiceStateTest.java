package com.dhl.g05.statemachine;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.communication.AbstractCommunicationFactory;
import com.dhl.g05.communication.CommunicationFactory;
import com.dhl.g05.database.AbstractDataBaseFactory;
import com.dhl.g05.database.DataBaseFactory;
import com.dhl.g05.filehandler.LeagueModelJson;
import com.dhl.g05.mocks.MockPlayerCommunication;

public class PlayerChoiceStateTest {
	private AbstractState state;
	private static MockPlayerCommunication communicate;
	
	
	 @BeforeClass
	    public static void setup() {
	        AbstractCommunicationFactory.setFactory(new CommunicationFactory());
	        AbstractDataBaseFactory.setFactory(new DataBaseFactory());
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
		state = AbstractStateMachineFactory.getFactory().getPlayerChoiceState();
	}

	@Test
	public void performStateTaskTest() {
		communicate.commandLineInput("5");
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof SimulateState);
	}

	@Test
	public void performStateTaskFailTest() {
		communicate.commandLineInput("Jeyanth");
		state.enter();
		assertFalse(state.performStateTask());
	}
	
}
