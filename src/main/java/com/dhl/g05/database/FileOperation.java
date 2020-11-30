package com.dhl.g05.database;

import java.io.File;

public class FileOperation implements IFileOperation{

	@Override
	public String getFilePath(String teamName) {
		String path = teamName+FileOperationConstant.FileExtension.getValue();
		return path;
	}

	@Override
	public boolean isFileExist(String name) {
		File file = new File(getFilePath(name));
		if(file.exists()) {
			return true;
		}
		return false;
	}

}
