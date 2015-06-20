package com.github.vincent_fuchs.spring_projects.excelReader;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.github.vincent_fuchs.spring_projects.domain.Customer;

public class ExcelReaderTest {
	
	private static final String SIMPLE_INPUT_FILE = "/com/github/vincent_fuchs/spring_projects/excelReader/simpleInputFile.xlsx";
	ExcelReader excelReader;
	
	@Before
	public void initTest(){
		excelReader=new ExcelReader();
		excelReader.setInputFile(SIMPLE_INPUT_FILE);
	}
	
	@Test(expected=IllegalStateException.class)
	public void shouldThrowException_ifNotProperlyConfigured_notExistingInputFile() throws IOException {
		
		excelReader.setInputFile(null);		
		excelReader.init();		
	}
	
	@Test(expected=IOException.class)
	public void shouldThrowException_ifFileDoesntExist() throws IOException  {
				
		excelReader.setInputFile("nonExistingFile.xlsx");
		excelReader.init();		
	}
	
	
	@Test(expected=IllegalStateException.class)
	public void shouldThrowException_ifNoWorksheetConfig() throws IOException {
								
		excelReader.init();		
	
	}
	
	@Test
	public void shouldInitOk_ifWorksheetConfigDone() throws IOException {
					
		excelReader.setWorsheetConfigs(buildWorksheetConfigsWithNames("customers"));
		
		excelReader.init();		
	}
	
	
	@Test(expected=ExcelReaderConfigException.class)
	public void shouldThrowException_ifConfiguredWorksheetDoesntExist() throws IOException, EncryptedDocumentException, ExcelReaderConfigException, InvalidFormatException{
						
		excelReader.setWorsheetConfigs(buildWorksheetConfigsWithNames("someDummyNonExistingWorksheet"));		
		
		excelReader.init();		
		excelReader.readWorksheets();
	}
		
	
	@Test(expected=ExcelReaderConfigException.class)
	public void shouldThrowException_ifConfiguredWorksheetHasNoParser() throws IOException, EncryptedDocumentException, ExcelReaderConfigException, InvalidFormatException{
					
		List<WorksheetConfig>  worksheetsConfig=buildWorksheetConfigsWithNames("customers");		
				
		excelReader.setWorsheetConfigs(worksheetsConfig);		
		
		excelReader.init();		
		excelReader.readWorksheets();
	}
	
	@Test
	public void shouldHaveAnEntryInResultWithSameNameAsWorksheetName() throws IOException, EncryptedDocumentException, ExcelReaderConfigException, InvalidFormatException{

		List<WorksheetConfig> config=new ArrayList<WorksheetConfig>();
		
		config.add(new WorksheetConfig("customers",Customer.class));
				
		excelReader.setWorsheetConfigs(config);		
		
		excelReader.init();		
		Map<String, List<Object>> result=excelReader.readWorksheets();
		
		assertThat(result).isNotEmpty();
		assertThat(result).containsKey("customers");
	}
	
	
	
	
	private List<WorksheetConfig>  buildWorksheetConfigsWithNames(String... worksheetNames) {
		
		List<WorksheetConfig> config=new ArrayList<WorksheetConfig>();
		
		for(String worksheetName : worksheetNames){
			config.add(new WorksheetConfig(worksheetName));
		}
		
		return config;
	}
	
	

}
