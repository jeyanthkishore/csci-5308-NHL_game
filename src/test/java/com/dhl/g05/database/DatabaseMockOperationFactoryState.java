package com.dhl.g05.database;

public class DatabaseMockOperationFactoryState extends DatabaseState {

	@Override
	public DatabaseAbstractFactory concreteMethod() {
		return new DataBaseMockOperationFactory();
	}

}
