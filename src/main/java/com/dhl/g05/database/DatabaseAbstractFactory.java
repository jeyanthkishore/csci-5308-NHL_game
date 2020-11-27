package com.dhl.g05.database;

public abstract class DatabaseAbstractFactory {
	private static DatabaseAbstractFactory databaseAbstractFactory;
	
	public static DatabaseAbstractFactory getInstance(DatabaseState state) {
		databaseAbstractFactory = state.concreteMethod();
		return databaseAbstractFactory;
	}

	public abstract ISerializeModel getSerializeModel();
	public abstract IFileOperation getFileDetails();
	public abstract ICheckTeam getCheckTeam();
	public abstract IDeserializeModel getDeserializeModel();
	
}
