package com.dhl.g05.leaguemodel;

import com.dhl.g05.operation.OperationModel;

public interface IDataBasePersistence {

	public void loadModel(OperationModel operationModel);

	public void saveModel(OperationModel operationModel);

	public boolean checkLeagueExistence(OperationModel operationModel);

	public void loadNewTeams(OperationModel operationModel);


}
