package com.dhl.g05.database;

public enum FileOperationMockConstant {

	DirectoryPath("src/test/java/com/dhl/g05/jsontestfiles/"),
	FileExtension(".json");
	
	private String value; 

	FileOperationMockConstant(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
