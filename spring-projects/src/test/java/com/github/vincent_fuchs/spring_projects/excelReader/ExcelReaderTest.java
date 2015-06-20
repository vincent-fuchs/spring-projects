package com.github.vincent_fuchs.spring_projects.excelReader;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.net.URL;

import org.junit.Test;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class ExcelReaderTest {

	@Test(expected=IllegalStateException.class)
	public void shouldThrowException_ifNotProperlyConfigured_notExistingInputFile() throws UnexpectedInputException, ParseException, NonTransientResourceException, Exception {
		
		ExcelReader excelReader=new ExcelReader();
		
		excelReader.init();		
	}
	
	@Test(expected=IOException.class)
	public void shouldThrowException_ifFileDoesntExist() throws UnexpectedInputException, ParseException, NonTransientResourceException, Exception {
		
		ExcelReader excelReader=new ExcelReader();
		excelReader.setInputFile("nonExistingFile.xlsx");
		excelReader.init();		
		

	}
	
	
	@Test
	public void shouldInitOk_withValidExistingFile() throws UnexpectedInputException, ParseException, NonTransientResourceException, Exception {
		
		ExcelReader excelReader=new ExcelReader();
				
		excelReader.setInputFile("/com/github/vincent_fuchs/spring_projects/excelReader/simpleInputFile.xlsx");
		excelReader.init();		
	
	}
	
	
	

}
