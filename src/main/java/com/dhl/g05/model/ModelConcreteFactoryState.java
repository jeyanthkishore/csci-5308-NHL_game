package com.dhl.g05.model;

public class ModelConcreteFactoryState extends ModelState{

	@Override
	public ModelAbstractFactory concreteMethod() {
		return new ModelConcreteFactory();
	}

}
