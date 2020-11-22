package com.dhl.g05.statemachine;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.communication.AbstractCommunicationFactory;
import com.dhl.g05.communication.CommunicationFactory;
import com.dhl.g05.db.AbstractDataBaseFactory;
import com.dhl.g05.filehandler.LeagueModelJson;
import com.dhl.g05.mockdata.JsonMockDataDb;

import filehandler.DatabaseMockFactory;

public class TradeStateTest {
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
		state = AbstractStateMachineFactory.getFactory().getTradeState();
	}
	
	@Test
	public void performTaskTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		state.setLeague(data.league);
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof AgingState);
	}
}
