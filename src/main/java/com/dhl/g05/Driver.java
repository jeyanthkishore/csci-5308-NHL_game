package com.dhl.g05;

import com.dhl.g05.communication.AbstractCommunicationFactory;
import com.dhl.g05.communication.CommunicationFactory;
import com.dhl.g05.db.AbstractDataBaseFactory;
import com.dhl.g05.db.DataBaseFactory;
import com.dhl.g05.filehandler.LeagueModelJson;
import com.dhl.g05.gameplayconfig.AbstractGamePlayConfigFactory;
import com.dhl.g05.gameplayconfig.GamePlayConfigFactory;
import com.dhl.g05.player.AbstractPlayerFactory;
import com.dhl.g05.player.PlayerFactory;
import com.dhl.g05.statemachine.AbstractState;
import com.dhl.g05.statemachine.AbstractStateMachineFactory;
import com.dhl.g05.statemachine.StateMachineFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Driver {
	static final Logger logger = LogManager.getLogger(Driver.class);
	public static void main(String[] args) {
		logger.info("Test-Driver-Info");
		logger.error("Test-Driver-Error");
		logger.debug("Test-Driver-Debug");
		AbstractCommunicationFactory.setFactory(new CommunicationFactory());
		AbstractDataBaseFactory.setFactory(new DataBaseFactory());
		AbstractGamePlayConfigFactory.setFactory(new GamePlayConfigFactory());
		AbstractPlayerFactory.setFactory(new PlayerFactory());
		AbstractStateMachineFactory.setFactory(
				new StateMachineFactory(
						AbstractCommunicationFactory.getFactory().getCommunication(),
						new LeagueModelJson()
				)
		);
		AbstractState importState = AbstractStateMachineFactory.getFactory().getImportState();
		AbstractStateMachineFactory.getFactory().getStateMachine(importState).enterState();
	}
}