package com.github.vincent_fuchs.spring_projects.excelReader;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class ExcelReaderTest {
	
	ExcelReader excelReader;
	
	@Before
	public void initTest(){
		excelReader=new ExcelReader();
	}
	
	@Test(expected=IllegalStateException.class)
	public void shouldThrowException_ifNotProperlyConfigured_notExistingInputFile() throws UnexpectedInputException, ParseException, NonTransientResourceException, Exception {
			
		excelReader.init();		
	}
	
	@Test(expected=IOException.class)
	public void shouldThrowException_ifFileDoesntExist() throws UnexpectedInputException, ParseException, NonTransientResourceException, Exception {
				
		excelReader.setInputFile("nonExistingFile.xlsx");
		excelReader.init();		
	}
	
	
	@Test
	public void shouldInitOk_withValidExistingFile() throws UnexpectedInputException, ParseException, NonTransientResourceException, Exception {
						
		excelReader.setInputFile("/com/github/vincent_fuchs/spring_projects/excelReader/simpleInputFile.xlsx");
		excelReader.init();		
	
	}
	
	
	

}
