package com.dhl.g05.statemachine;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dhl.g05.communication.AbstractCommunicationFactory;
import com.dhl.g05.communication.CommunicationFactory;
import com.dhl.g05.communication.IPlayerCommunication;
import com.dhl.g05.database.AbstractDataBaseFactory;
import com.dhl.g05.database.DataBaseFactory;
import com.dhl.g05.filehandler.LeagueModelJson;
import com.dhl.g05.mockdata.JsonMockDataDb;
import com.dhl.g05.mocks.MockCreateTeamCommnication;
import com.dhl.g05.mocks.MockPlayerCommunication;

public class CreateTeamStateTest {
	private static MockPlayerCommunication communicate;
	IStateMachine machine;
	private static CreateTeamState create;


	 public static void setup(String userInput) {
		    communicate = new MockPlayerCommunication();
		    communicate.commandLineInput(userInput);
	        AbstractCommunicationFactory.setFactory(new CommunicationFactory());
	        AbstractDataBaseFactory.setFactory(new DataBaseFactory());
	        AbstractStateMachineFactory.setFactory(
	                new StateMachineFactory(
	                		AbstractCommunicationFactory.getFactory().getCommunication(),
	                		new LeagueModelJson()
	                )
	        );
	        create = new CreateTeamState(new MockCreateTeamCommnication());
			JsonMockDataDb data = new JsonMockDataDb();
			create.setLeague(data.league);
	    }

	 @Test
	 public void performTaskTest() {
		 String userInput = "Western Conference\nPacific\nKillers";
		 setup(userInput);
		 create.enter();
		 IPlayerCommunication com = new MockPlayerCommunication();
		 create.setCommunicate(com);
		 create.performStateTask();
		 assertTrue(create.exit());
	 }
	 
	 @Test
	 public void divisionNotExistTest() {
		 String userInput = "Western Conference\nDummy\nKillers";
		 setup(userInput);
		 create.enter();
		 assertFalse(create.performStateTask());
	 }

	 @Test
	 public void conferenceNullTest() {
		 String userInput = "Western Conference\n\nKillers";
		 setup(userInput);
		 create.enter();
		 assertFalse(create.performStateTask());
	 }
}
