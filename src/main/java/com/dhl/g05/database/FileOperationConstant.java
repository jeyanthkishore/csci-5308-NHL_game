package com.dhl.g05.database;

public enum FileOperationConstant {

	DirectoryPath("src/main/java/com/dhl/g05/database/files/"),
	FileExtension(".json");
	
	private String value; 

	FileOperationConstant(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
