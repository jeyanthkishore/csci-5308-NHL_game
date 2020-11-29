package com.dhl.g05.database;

public class FileOperationMock implements IFileOperation {

	@Override
	public String getFilePath(String teamName) {
		return "src/test/java/com/dhl/g05/jsontestfiles/OutputModel.json";
	}

	@Override
	public boolean isFileExist(String path) {
		return true;
	}

}
