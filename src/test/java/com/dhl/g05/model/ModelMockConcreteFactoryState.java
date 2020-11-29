package com.dhl.g05.model;

public class ModelMockConcreteFactoryState extends ModelMockState{

	@Override
	public ModelMockAbstractFactory concreteMethod() {
		return new ModelMockConcreteFactory();
	}

}
