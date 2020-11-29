package com.dhl.g05.model;

public class ModelMockConcreteFactory extends ModelMockAbstractFactory{

	@Override
	public LeagueMockData createLeagueMockData() {
		return new LeagueMockData();
	}

	@Override
	public PlayerTrainingMockData createPlayerTrainingMock() {
		return new PlayerTrainingMockData();
	}

}
