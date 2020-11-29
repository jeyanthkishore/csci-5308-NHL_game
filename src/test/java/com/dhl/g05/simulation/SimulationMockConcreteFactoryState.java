package com.dhl.g05.simulation;

public class SimulationMockConcreteFactoryState extends SimulationMockState{

	@Override
	public SimulationMockAbstractFactory concreteMethod() {
		return new SimulationMockConcreteFactory();
	}

}
