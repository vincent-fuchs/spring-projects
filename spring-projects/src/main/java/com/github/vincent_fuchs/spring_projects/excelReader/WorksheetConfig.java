package com.github.vincent_fuchs.spring_projects.excelReader;

import org.apache.poi.hssf.util.CellReference;

public class WorksheetConfig {

	private String name;
	
	private String firstCellWithData="A2";
	
	public void setFirstCellWithData(String firstCellWithData) {
		this.firstCellWithData = firstCellWithData;
	}

	private Class rowClass;

	public WorksheetConfig(String worksheetName) {
		
		name=worksheetName;
		
	}

	public WorksheetConfig(String worksheetName, Class configuredRowClass) {
		name=worksheetName;
		rowClass=configuredRowClass;
	}

	public String getName() {
		
		return name;
	}

	public Class getRowClass() {
		return rowClass;
	}

	public int getFirstCellWithDataRowIndex() {
		
		CellReference ref = new CellReference(firstCellWithData);
		
		return ref.getRow();
	}

	public int getFirstCellWithDataColumnIndex() {
		
		CellReference ref = new CellReference(firstCellWithData);
		
		return ref.getCol();
		
	}

	

}
