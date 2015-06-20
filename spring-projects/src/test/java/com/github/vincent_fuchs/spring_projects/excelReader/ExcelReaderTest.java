package com.github.vincent_fuchs.spring_projects.excelReader;

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
	
	

}
