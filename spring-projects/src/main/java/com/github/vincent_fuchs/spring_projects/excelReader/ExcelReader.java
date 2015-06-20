package com.github.vincent_fuchs.spring_projects.excelReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.apache.commons.lang3.StringUtils;

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

	public Map<String,List<Object>> readWorksheets() throws IOException, ExcelReaderConfigException, EncryptedDocumentException, InvalidFormatException {
		
		Map<String,List<Object>> parsedResultFromWorksheets=new HashMap<String, List<Object>>();
		
		Workbook workbook = WorkbookFactory.create(excelFileAsStream);
		
		for(WorksheetConfig worksheetConfig : worsheetConfigs){
		
			Sheet sheet=validateAndReturnSheet(workbook, worksheetConfig);

			List<Object> parsedResult;
			String worksheetName = worksheetConfig.getName();
			if(!parsedResultFromWorksheets.containsKey(worksheetName)){
				parsedResult=new ArrayList<Object>();
				parsedResultFromWorksheets.put(worksheetName,parsedResult);
			}
			else{
				parsedResult=parsedResultFromWorksheets.get(worksheetName);
			}
			
			for(int i=worksheetConfig.getFirstCellWithDataColumnIndex() ; i<=sheet.getLastRowNum() ; i++){
			
				Row rowToParse=sheet.getRow(i);
				parsedResult.add(rowToParse);
								
			}
			
			
			
			
		}
		
		return parsedResultFromWorksheets;
		
		
		
		
	}
//
//	private Row findFirstRow(Sheet sheet, WorksheetConfig worksheetConfig) {
//		
//		String firstCell=worksheetConfig.getFirstCellWithData();
//		
//		
//		
//	}

	private Sheet validateAndReturnSheet(Workbook workbook,
			WorksheetConfig worksheetConfig) {
		
		String configuredSheetName=worksheetConfig.getName();
		
		Sheet sheet=workbook.getSheet(configuredSheetName);
		
		if(workbook.getSheet(configuredSheetName)==null){
										
			throw new ExcelReaderConfigException("configured worksheet with name "+configuredSheetName+" not found in input file "+inputFile+
					". existing sheets : "+joinExistingWorksheetsNames(workbook));
		}
		
		if(worksheetConfig.getRowClass()==null){
			throw new ExcelReaderConfigException("configured worksheet with name "+configuredSheetName+" should have a configured rowClass but it's null");
		}
		
		return sheet;
	}

	private String joinExistingWorksheetsNames(Workbook workbook) {
		List<String> existingSheetNames=new ArrayList<String>();
		int i=0;
		
		while(i<workbook.getNumberOfSheets()){
			existingSheetNames.add(workbook.getSheetAt(i).getSheetName());
			i++;
		}
		return StringUtils.join(existingSheetNames,",");
	}

}
