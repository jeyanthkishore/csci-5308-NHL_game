package com.dhl.g05;

import com.dhl.g05.communication.CommunicationAbstractFactory;
import com.dhl.g05.communication.CommunicationFactoryState;
import com.dhl.g05.communication.CommunicationState;
import com.dhl.g05.database.DatabaseAbstractFactory;
import com.dhl.g05.database.DatabaseFactoryState;
import com.dhl.g05.database.DatabaseState;
import com.dhl.g05.player.AbstractPlayerFactory;
import com.dhl.g05.player.PlayerFactory;
import com.dhl.g05.statemachine.StateMachineAbstractFactory;
import com.dhl.g05.statemachine.StateMachineFactoryState;
import com.dhl.g05.statemachine.StateMachineState;
import com.dhl.g05.trading.AbstractTradingFactory;
import com.dhl.g05.trading.TradingFactory;

public class ApplicationConfiguration {
	private static ApplicationConfiguration uniqueInstance = null;
	private StateMachineState statemachine;
	private DatabaseState database;
	private CommunicationState communication;
	
	private ApplicationConfiguration() {
		statemachine = new StateMachineFactoryState();
		database = new DatabaseFactoryState();
		communication = new CommunicationFactoryState();
        AbstractPlayerFactory.setFactory(new PlayerFactory());
        AbstractTradingFactory.setFactory(new TradingFactory());
	}
	
	public static ApplicationConfiguration instance() {
		if(uniqueInstance == null) {
			uniqueInstance = new ApplicationConfiguration();
		}
		return uniqueInstance;
	}
	
	public StateMachineAbstractFactory getStateMachineFactoryState() {
		return StateMachineAbstractFactory.getInstance(statemachine);
	}
	
	public DatabaseAbstractFactory getDatabaseFactoryState() {
		return DatabaseAbstractFactory.getInstance(database);
	}
	
	public CommunicationAbstractFactory getCommunicationFactoryState() {
		return CommunicationAbstractFactory.getInstance(communication);
	}
	
	public CommunicationState getCommunicationState() {
		return new CommunicationFactoryState();
	}
	
	public void setDataBaseFactoryState(DatabaseState newDatabase) {
		database = newDatabase;
	}
	
	public void setCommunicationFactoryState(CommunicationState newCommunication) {
		communication = newCommunication;
	}
}
