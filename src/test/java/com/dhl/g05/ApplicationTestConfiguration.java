package com.dhl.g05;

import com.dhl.g05.communication.CommunicationMockAbstractFactory;
import com.dhl.g05.communication.CommunicationMockConcreteFactoryState;
import com.dhl.g05.communication.CommunicationMockState;
import com.dhl.g05.database.DatabaseMockAbstractFactory;
import com.dhl.g05.database.DatabaseMockConcreteFactoryState;
import com.dhl.g05.database.DatabaseMockState;
import com.dhl.g05.model.ModelMockAbstractFactory;
import com.dhl.g05.model.ModelMockConcreteFactoryState;
import com.dhl.g05.model.ModelMockState;
import com.dhl.g05.simulation.SimulationMockAbstractFactory;
import com.dhl.g05.simulation.SimulationMockConcreteFactoryState;
import com.dhl.g05.simulation.SimulationMockState;

public class ApplicationTestConfiguration {
	private static ApplicationTestConfiguration uniqueInstance = null;
	private SimulationMockState simulation;
	private DatabaseMockState database;
	private ModelMockState model;
	private CommunicationMockState communication;

	private ApplicationTestConfiguration() {
		simulation = new SimulationMockConcreteFactoryState();
		model = new ModelMockConcreteFactoryState();
		database = new DatabaseMockConcreteFactoryState();
		communication = new CommunicationMockConcreteFactoryState();
	}

	public static ApplicationTestConfiguration instance() {
		if(uniqueInstance == null) {
			uniqueInstance = new ApplicationTestConfiguration();
		}
		return uniqueInstance;
	}

	public SimulationMockAbstractFactory getSimulationMockConcreteFactoryState() {
		return SimulationMockAbstractFactory.instance(simulation);
	}

	public ModelMockAbstractFactory getModelMockConcreteFactoryState() {
		return ModelMockAbstractFactory.instance(model);
	}
	
	public DatabaseMockAbstractFactory getDatabaseMockConcreteFactoryState() {
		return DatabaseMockAbstractFactory.instance(database);
	}
	
	public CommunicationMockAbstractFactory getCommunicationMockConcreteFactoryState() {
		return CommunicationMockAbstractFactory.instance(communication);
	}

}
