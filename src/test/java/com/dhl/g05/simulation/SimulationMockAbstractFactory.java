package com.dhl.g05.simulation;

import com.dhl.g05.simulation.leaguesimulation.StandingMockData;

public abstract class SimulationMockAbstractFactory {
	
	private static SimulationMockAbstractFactory simulationMockAbstractFactory;

	public static SimulationMockAbstractFactory getInstance(SimulationMockState state) {
		simulationMockAbstractFactory = state.concreteMethod();
		return simulationMockAbstractFactory;
	}
	
	public abstract StandingMockData createStandingMock();
	
}
