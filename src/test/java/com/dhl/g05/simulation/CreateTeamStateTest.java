package com.dhl.g05.simulation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;

import org.junit.AfterClass;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.ApplicationTestConfiguration;
import com.dhl.g05.communication.CommunicationPlayerMockFactoryState;
import com.dhl.g05.communication.CommunicationState;
import com.dhl.g05.communication.CommunicationTeamMockFactoryState;
import com.dhl.g05.database.DatabaseMockFactoryState;
import com.dhl.g05.database.DatabaseState;
import com.dhl.g05.model.LeagueMockData;
import com.dhl.g05.model.ModelMockAbstractFactory;

public class CreateTeamStateTest {
	IStateMachine machine;
	private static AbstractState create;

	/*Function called after setting up the ByteArrayInputStream to manipulate user input*/
	 public static void setup() {
		 	CommunicationState communication = new CommunicationTeamMockFactoryState();
		    ApplicationConfiguration.instance().setCommunicationFactoryState(communication);
		    DatabaseState state = new DatabaseMockFactoryState();
			ApplicationConfiguration.instance().setDataBaseFactoryState(state);
		    SimulationAbstractFactory stateFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		    ModelMockAbstractFactory modelMockFactory = ApplicationTestConfiguration.instance().getModelMockConcreteFactoryState();
		    create = stateFactory.createCreateTeamState();
	        LeagueMockData data = modelMockFactory.createLeagueMockData();
			create.setLeague(data.league);
	    }
	 
	 @AfterClass
	 public static void setCommunication() {
		 CommunicationState communication = new CommunicationPlayerMockFactoryState();
		 ApplicationConfiguration.instance().setCommunicationFactoryState(communication);
	 }

	 @Test
	 public void divisionNotExistTest() {
		 String userInput = "Western Conference\nDummy\nKillers";
		 ByteArrayInputStream testInput = new ByteArrayInputStream(userInput.getBytes());
	     System.setIn(testInput);
	     setup();
		 create.enter();
		 assertFalse(create.performStateTask());
	 }

	 @Test
	 public void conferenceNullTest() {
		 String userInput = "Western Conference\n\nKillers";
		 ByteArrayInputStream testInput = new ByteArrayInputStream(userInput.getBytes());
	     System.setIn(testInput);
		 setup();
		 create.enter();
		 assertFalse(create.performStateTask());
	 }
	 
	 @Test
	 public void teamNameNotUniqueTest() {
		 String userInput = "Western Conference\nPacific\nTeamName\nKillers\nYes";
		 ByteArrayInputStream testInput = new ByteArrayInputStream(userInput.getBytes());
	     System.setIn(testInput);
		 setup();
		 create.enter();
		 create.performStateTask();
		 assertTrue(create.exit());
		 assertTrue(create.getNextState() instanceof PlayerChoiceState);
	 }
}
