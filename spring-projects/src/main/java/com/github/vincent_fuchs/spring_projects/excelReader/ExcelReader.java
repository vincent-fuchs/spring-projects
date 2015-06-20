package com.github.vincent_fuchs.spring_projects.excelReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.util.StringUtils;

import com.github.vincent_fuchs.spring_projects.domain.Customer;

public class ExcelReader implements ItemReader<Customer> {

	private String inputFile;

	@Override
	public Customer read() throws Exception, UnexpectedInputException, ParseException,
			NonTransientResourceException {
		// TODO Auto-generated method stub
		return null;
	}

	public void init() throws IOException  {
		
		if(StringUtils.isEmpty(inputFile)){
			throw new IllegalStateException("reader should have a valid inputFile configured");
		}
		
		URL fileAsUrl=this.getClass().getResource(inputFile);
		
		if(fileAsUrl==null){
			throw new IOException("file doesn't exist : "+inputFile);
		}
				
		FileInputStream excelFileAsStream = new FileInputStream(fileAsUrl.getFile());
		
		
	}

	public void setInputFile(String inputFile) {
		this.inputFile=inputFile;
		
	}

}
