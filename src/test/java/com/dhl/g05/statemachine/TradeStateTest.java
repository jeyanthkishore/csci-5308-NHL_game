package com.dhl.g05.statemachine;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.communication.AbstractCommunicationFactory;
import com.dhl.g05.communication.CommunicationFactory;
import com.dhl.g05.database.AbstractDataBaseFactory;
import com.dhl.g05.database.DataBaseFactory;
import com.dhl.g05.filehandler.LeagueModelJson;
import com.dhl.g05.mockdata.JsonMockDataDb;
import com.dhl.g05.trading.AbstractTradingFactory;
import com.dhl.g05.trading.TradingFactory;

public class TradeStateTest {
	private AbstractState state;
	
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
	        AbstractTradingFactory.setFactory(new TradingFactory());
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
