package com.dhl.g05.database;

public class FileOperationMock implements IFileOperation {

	@Override
	public String getFilePath(String teamName) {
		String path = FileOperationMockConstant.DirectoryPath.getValue()+teamName
				+FileOperationMockConstant.FileExtension.getValue();
		return path;
	}

	@Override
	public boolean isFileExist(String path) {
		return true;
	}

}
