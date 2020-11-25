package com.dhl.g05.database;

public interface IFileOperation {

	String getFilePath(String teamName);

	boolean isFileExist(String name);

}
