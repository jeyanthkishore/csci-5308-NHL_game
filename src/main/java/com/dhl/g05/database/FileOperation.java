package com.dhl.g05.database;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileOperation implements IFileOperation{
	
	static final Logger logger = LogManager.getLogger(FileOperation.class);

	@Override
	public String getFilePath(String teamName) {
		logger.info("Fetching the file path from database");
		
		String path = FileOperationConstant.DirectoryPath.getValue()+teamName
				+FileOperationConstant.FileExtension.getValue();
		return path;
	}

	@Override
	public boolean isFileExist(String name) {
		logger.info("Checking file exists or not");
		
		File file = new File(getFilePath(name));
		if(file.exists()) {
			return true;
		}
		return false;
	}

}
