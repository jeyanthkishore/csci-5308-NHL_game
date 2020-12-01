package com.dhl.g05.database;

public class DatabaseConcreteFactoryState extends DatabaseState{

	@Override
	public DatabaseAbstractFactory concreteMethod() {
		return new DatabaseConcreteFactory();
	}

}
