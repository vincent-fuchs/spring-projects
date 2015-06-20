package com.github.vincent_fuchs.spring_projects.excelReader;

public class WorksheetConfig {

	private String name;
	
	private String firstCellWithData="B1";

	public WorksheetConfig(String worksheetName) {
		
		name=worksheetName;
		
	}

	public String getName() {
		
		return name;
	}

	public Object getWorksheetParser() {
		// TODO Auto-generated method stub
		return null;
	}

}
