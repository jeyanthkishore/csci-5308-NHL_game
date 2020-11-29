package com.dhl.g05;

import com.dhl.g05.model.ModelMockAbstractFactory;
import com.dhl.g05.model.ModelMockConcreteFactoryState;
import com.dhl.g05.model.ModelMockState;
import com.dhl.g05.simulation.SimulationMockAbstractFactory;
import com.dhl.g05.simulation.SimulationMockConcreteFactoryState;
import com.dhl.g05.simulation.SimulationMockState;

public class ApplicationTestConfiguration {
	private static ApplicationTestConfiguration uniqueInstance = null;
	private SimulationMockState simulation;
	//	private DatabaseState database;
	//	private CommunicationState communication;
	private ModelMockState model;

	private ApplicationTestConfiguration() {
		simulation = new SimulationMockConcreteFactoryState();
		model = new ModelMockConcreteFactoryState();
	}

	public static ApplicationTestConfiguration instance() {
		if(uniqueInstance == null) {
			uniqueInstance = new ApplicationTestConfiguration();
		}
		return uniqueInstance;
	}

	public SimulationMockAbstractFactory getSimulationMockConcreteFactoryState() {
		return SimulationMockAbstractFactory.getInstance(simulation);
	}

	public ModelMockAbstractFactory getModelMockConcreteFactoryState() {
		return ModelMockAbstractFactory.getInstance(model);
	}

}
