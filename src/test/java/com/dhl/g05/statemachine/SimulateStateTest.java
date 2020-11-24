package com.dhl.g05.statemachine;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.communication.AbstractCommunicationFactory;
import com.dhl.g05.communication.CommunicationFactory;
import com.dhl.g05.db.AbstractDataBaseFactory;
import com.dhl.g05.filehandler.LeagueModelJson;

import filehandler.DatabaseMockFactory;

public class SimulateStateTest {
	private AbstractState state;

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
	    }

	@Before
	public void init() {
		state = AbstractStateMachineFactory.getFactory().getStimulateState(0);
	}

	@Test
	public void testEnter() {
		state.enter();
		state.performStateTask();
		assertFalse(state.exit());
	}

}
