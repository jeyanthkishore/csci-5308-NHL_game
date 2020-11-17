package com.dhl.g05;

import com.dhl.g05.communication.AbstractCommunicationFactory;
import com.dhl.g05.communication.CommunicationFactory;
import com.dhl.g05.db.AbstractDataBaseFactory;
import com.dhl.g05.db.DataBaseFactory;
import com.dhl.g05.filehandler.LeagueModelJson;
import com.dhl.g05.statemachine.AbstractState;
import com.dhl.g05.statemachine.AbstractStateMachineFactory;
import com.dhl.g05.statemachine.StateMachineFactory;

public class Driver {
	public static void main(String[] args) {
		AbstractCommunicationFactory.setFactory(new CommunicationFactory());
		AbstractDataBaseFactory.setFactory(new DataBaseFactory());
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
