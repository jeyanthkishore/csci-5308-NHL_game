package com.dhl.g05;

import com.dhl.g05.communication.CommunicationAbstractFactory;
import com.dhl.g05.communication.CommunicationConcreteFactoryState;
import com.dhl.g05.communication.CommunicationState;
import com.dhl.g05.database.DatabaseAbstractFactory;
import com.dhl.g05.database.DatabaseConcreteFactoryState;
import com.dhl.g05.database.DatabaseState;
import com.dhl.g05.model.ModelAbstractFactory;
import com.dhl.g05.model.ModelConcreteFactoryState;
import com.dhl.g05.model.ModelState;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.dhl.g05.simulation.SimulationConcreteFactoryState;
import com.dhl.g05.simulation.SimulationState;
import com.dhl.g05.trading.TradeAbstractFactory;
import com.dhl.g05.trading.TradingFactory;

public class ApplicationConfiguration {
	private static ApplicationConfiguration uniqueInstance = null;
	private SimulationState simulation;
	private DatabaseState database;
	private CommunicationState communication;
	private ModelState model;
	
	private ApplicationConfiguration() {
		simulation = new SimulationConcreteFactoryState();
		database = new DatabaseConcreteFactoryState();
		communication = new CommunicationConcreteFactoryState();
		model = new ModelConcreteFactoryState();
        TradeAbstractFactory.setFactory(new TradingFactory());
	}
	
	public static ApplicationConfiguration instance() {
		if(uniqueInstance == null) {
			uniqueInstance = new ApplicationConfiguration();
		}
		return uniqueInstance;
	}
	
	public SimulationAbstractFactory getSimulationConcreteFactoryState() {
		return SimulationAbstractFactory.getInstance(simulation);
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
