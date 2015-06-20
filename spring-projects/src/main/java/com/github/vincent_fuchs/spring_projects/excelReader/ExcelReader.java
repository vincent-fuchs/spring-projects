package com.github.vincent_fuchs.spring_projects.excelReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.util.StringUtils;

import com.github.vincent_fuchs.spring_projects.domain.Customer;

public class ExcelReader implements ItemReader<Customer> {

	private String inputFile;
	
	private List<WorksheetConfig> worsheetConfigs=new ArrayList<WorksheetConfig>();
	
	private FileInputStream excelFileAsStream;

	public void setWorsheetConfigs(List<WorksheetConfig> worsheetConfigs) {
		this.worsheetConfigs = worsheetConfigs;
	}

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
		
		if(worsheetConfigs.isEmpty()){
			throw new IllegalStateException("At least one worksheet config is required to read the excel file");
		}
				
		excelFileAsStream = new FileInputStream(fileAsUrl.getFile());
		
		
	}

	public void setInputFile(String inputFile) {
		this.inputFile=inputFile;
		
	}

	public void readWorksheets() throws IOException, ExcelReaderConfigException, EncryptedDocumentException, InvalidFormatException {
		
		Workbook workbook = WorkbookFactory.create(excelFileAsStream);
		
		for(WorksheetConfig worksheetConfig : worsheetConfigs){
		
			String configuredSheetName=worksheetConfig.getName();
			
			if(workbook.getSheet(configuredSheetName)==null){
				throw new ExcelReaderConfigException("configured worksheet with name "+configuredSheetName+" not found in input file "+inputFile);
			}
			
		}
		
		
		
		
		
		
	}

}
