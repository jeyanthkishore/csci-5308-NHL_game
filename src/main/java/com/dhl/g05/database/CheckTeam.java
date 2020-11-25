package com.dhl.g05.database;

public class CheckTeam implements ICheckTeam{

	@Override
	public Boolean isTeamExist(String name) {
		IFileOperation fileCheck = AbstractDataBaseFactory.getFactory().getFileDetails();
		return fileCheck.isFileExist(name);
	}

}
