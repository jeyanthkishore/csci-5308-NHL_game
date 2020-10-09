package com.dhl.g05.operation;

public interface IDataBasePersistence {

	public void loadModel(OperationModel operationModel);

	public void saveModel(OperationModel operationModel);

	public boolean checkLeagueExistence(OperationModel operationModel);


}
