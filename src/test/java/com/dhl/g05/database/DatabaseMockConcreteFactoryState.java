package com.dhl.g05.database;

public class DatabaseMockConcreteFactoryState extends DatabaseMockState{

	@Override
	public DatabaseMockAbstractFactory concreteMethod() {
		return new DatabaseMockConcreteFactory();
	}

}
