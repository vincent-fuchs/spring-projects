package com.github.vincent_fuchs.spring_projects.excelReader;

public class WorksheetConfig {

	private String name;

	public WorksheetConfig(String worksheetName) {
		
		name=worksheetName;
		
	}

	public String getName() {
		
		return name;
	}

}
