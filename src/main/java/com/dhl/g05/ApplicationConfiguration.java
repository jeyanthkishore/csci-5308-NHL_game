package com.dhl.g05;

import com.dhl.g05.communication.CommunicationAbstractFactory;
import com.dhl.g05.communication.CommunicationConcreteFactoryState;
import com.dhl.g05.communication.CommunicationState;
import com.dhl.g05.database.DatabaseAbstractFactory;
import com.dhl.g05.database.DatabaseConcreteFactoryState;
import com.dhl.g05.database.DatabaseState;
import com.dhl.g05.model.AbstractPlayerFactory;
import com.dhl.g05.model.ModelAbstractFactory;
import com.dhl.g05.model.ModelConcreteFactoryState;
import com.dhl.g05.model.ModelState;
import com.dhl.g05.model.PlayerFactory;
import com.dhl.g05.simulation.StateMachineAbstractFactory;
import com.dhl.g05.simulation.StateMachineConcreteFactoryState;
import com.dhl.g05.simulation.StateMachineState;
import com.dhl.g05.trading.AbstractTradingFactory;
import com.dhl.g05.trading.TradingFactory;

public class ApplicationConfiguration {
	private static ApplicationConfiguration uniqueInstance = null;
	private StateMachineState statemachine;
	private DatabaseState database;
	private CommunicationState communication;
	private ModelState model;
	
	private ApplicationConfiguration() {
		statemachine = new StateMachineConcreteFactoryState();
		database = new DatabaseConcreteFactoryState();
		communication = new CommunicationConcreteFactoryState();
		model = new ModelConcreteFactoryState();
        AbstractPlayerFactory.setFactory(new PlayerFactory());
        AbstractTradingFactory.setFactory(new TradingFactory());
	}
	
	public static ApplicationConfiguration instance() {
		if(uniqueInstance == null) {
			uniqueInstance = new ApplicationConfiguration();
		}
		return uniqueInstance;
	}
	
	public StateMachineAbstractFactory getStateMachineConcreteFactoryState() {
		return StateMachineAbstractFactory.getInstance(statemachine);
	}
	
	public DatabaseAbstractFactory getDatabaseConcreteFactoryState() {
		return DatabaseAbstractFactory.getInstance(database);
	}
	
	public CommunicationAbstractFactory getCommunicationConcreteFactoryState() {
		return CommunicationAbstractFactory.getInstance(communication);
	}
	
	public ModelAbstractFactory getModelConcreteFactoryState() {
		return ModelAbstractFactory.getInstance(model);
	}
	
	public void setDataBaseFactoryState(DatabaseState newDatabase) {
		database = newDatabase;
	}
	
	public void setCommunicationFactoryState(CommunicationState newCommunication) {
		communication = newCommunication;
	}
}
