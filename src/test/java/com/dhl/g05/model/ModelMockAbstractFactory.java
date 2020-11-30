package com.dhl.g05.model;

public abstract class ModelMockAbstractFactory {

	private static ModelMockAbstractFactory modelMockAbstractFactory;

	public static ModelMockAbstractFactory getInstance(ModelMockState state) {
		modelMockAbstractFactory = state.concreteMethod();
		return modelMockAbstractFactory;
	}

	public abstract LeagueMockData createLeagueMockData();

	public abstract PlayerTrainingMockData createPlayerTrainingMock();

	public abstract PlayerDraftMock createPlayerDraftMock();
}
