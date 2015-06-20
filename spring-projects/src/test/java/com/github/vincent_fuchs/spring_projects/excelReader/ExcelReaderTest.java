package com.github.vincent_fuchs.spring_projects.excelReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Before;
import org.junit.Test;

public class ExcelReaderTest {
	
	private static final String SIMPLE_INPUT_FILE = "/com/github/vincent_fuchs/spring_projects/excelReader/simpleInputFile.xlsx";
	ExcelReader excelReader;
	
	@Before
	public void initTest(){
		excelReader=new ExcelReader();
	}
	
	@Test(expected=IllegalStateException.class)
	public void shouldThrowException_ifNotProperlyConfigured_notExistingInputFile() throws IOException {
			
		excelReader.init();		
	}
	
	@Test(expected=IOException.class)
	public void shouldThrowException_ifFileDoesntExist() throws IOException  {
				
		excelReader.setInputFile("nonExistingFile.xlsx");
		excelReader.init();		
	}
	
	
	@Test(expected=IllegalStateException.class)
	public void shouldThrowException_ifNoWorksheetConfig() throws IOException {
						
		excelReader.setInputFile(SIMPLE_INPUT_FILE);
		excelReader.init();		
	
	}
	
	@Test
	public void shouldInitOk_ifWorksheetConfigDone() throws IOException {
						
		excelReader.setInputFile(SIMPLE_INPUT_FILE);
		
		WorksheetConfig worksheetConfig=new WorksheetConfig("customers");
		
		List<WorksheetConfig> config=new ArrayList<WorksheetConfig>();
		config.add(worksheetConfig);
		excelReader.setWorsheetConfigs(config);		
		
		excelReader.init();		
	}
	
	@Test(expected=ExcelReaderConfigException.class)
	public void shouldThrowException_ifConfiguredWorksheetDoesntExist() throws IOException, EncryptedDocumentException, ExcelReaderConfigException, InvalidFormatException{
						
		excelReader.setInputFile(SIMPLE_INPUT_FILE);
		
		WorksheetConfig worksheetConfig=new WorksheetConfig("someDummyNonExistingWorksheet");
		
		List<WorksheetConfig> config=new ArrayList<WorksheetConfig>();
		config.add(worksheetConfig);
		excelReader.setWorsheetConfigs(config);		
		
		excelReader.init();		
		excelReader.readWorksheets();
	}
	
	
	
	

}
