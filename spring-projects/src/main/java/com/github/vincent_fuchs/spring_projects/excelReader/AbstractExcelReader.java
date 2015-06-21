package com.github.vincent_fuchs.spring_projects.excelReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;

import com.github.vincent_fuchs.spring_projects.customerBatch.domain.Customer;

public abstract class AbstractExcelReader implements ItemReader<Customer>{

	@Value("${customerBatch.inputFileName}")
	private String inputFile;
	
	private int readItemCount=0;

	private List<WorksheetConfig> worsheetConfigs = new ArrayList<WorksheetConfig>();

	private FileInputStream excelFileAsStream;

	protected Map<String, List<Object>> parsedResultFromWorksheets;
	
	private List<Object> results;

	
	/**
	 * Subclass have to implement this method, which will hold their business logic. 
	 * individual worksheets content is stored in {@link #parsedResultFromWorksheets} Worksheets map, where keys are the worksheets' names
	 * @return
	 */
	public abstract List<Object> mergeWorksheets() ;
	


	@Override
	public Customer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		
		if(results==null){
			parsedResultFromWorksheets=readWorksheets();
			results=mergeWorksheets(); 
		}
		
		if(readItemCount<results.size()){
			
			Customer customer=(Customer)results.get(readItemCount);
			readItemCount++;
			return customer;
		}
		
		return null;		
	}

	
	
	public void setWorsheetConfigs(List<WorksheetConfig> worsheetConfigs) {
		this.worsheetConfigs = worsheetConfigs;
	}


	@PostConstruct
	public void init() throws IOException {

		if (StringUtils.isEmpty(inputFile)) {
			throw new IllegalStateException(
					"reader should have a valid inputFile configured");
		}

		URL fileAsUrl = this.getClass().getResource(inputFile);

		if (fileAsUrl == null) {
			throw new IOException("file doesn't exist : " + inputFile);
		}

		if (worsheetConfigs.isEmpty()) {
			throw new IllegalStateException(
					"At least one worksheet config is required to read the excel file");
		}

		excelFileAsStream = new FileInputStream(fileAsUrl.getFile());

	}

	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;

	}

	protected Map<String, List<Object>> readWorksheets() throws IOException,
			ExcelReaderConfigException, EncryptedDocumentException,
			InvalidFormatException, InstantiationException,
			IllegalAccessException, SecurityException, NoSuchFieldException, IllegalArgumentException {

		parsedResultFromWorksheets = new HashMap<String, List<Object>>();

		Workbook workbook = WorkbookFactory.create(excelFileAsStream);

		ExcelRowParser excelRowParser=new ExcelRowParser();
		
		for (WorksheetConfig worksheetConfig : worsheetConfigs) {

			Sheet sheet = validateAndReturnSheet(workbook, worksheetConfig);

			List<Object> parsedResult = new ArrayList<Object>();
			for (int i = worksheetConfig.getFirstCellWithDataRowIndex(); i <= sheet.getLastRowNum(); i++) {

				Row rowToParse = sheet.getRow(i);

				Object parsedRow = excelRowParser.instanciateAndPopulateWithValuesFromRow(worksheetConfig.getRowClass(), rowToParse);

				parsedResult.add(parsedRow);

			}

			parsedResultFromWorksheets.put(worksheetConfig.getName(),parsedResult);

		}

		return parsedResultFromWorksheets;

	}
	

	private Sheet validateAndReturnSheet(Workbook workbook,	WorksheetConfig worksheetConfig) {

		String configuredSheetName = worksheetConfig.getName();

		Sheet sheet = workbook.getSheet(configuredSheetName);

		if (workbook.getSheet(configuredSheetName) == null) {

			throw new ExcelReaderConfigException(
					"configured worksheet with name " + configuredSheetName
							+ " not found in input file " + inputFile
							+ ". existing sheets : "
							+ joinExistingWorksheetsNames(workbook));
		}

		if (worksheetConfig.getRowClass() == null) {
			throw new ExcelReaderConfigException(
					"configured worksheet with name "
							+ configuredSheetName
							+ " should have a configured rowClass but it's null");
		}

		return sheet;
	}

	private String joinExistingWorksheetsNames(Workbook workbook) {
		List<String> existingSheetNames = new ArrayList<String>();
		int i = 0;

		while (i < workbook.getNumberOfSheets()) {
			existingSheetNames.add(workbook.getSheetAt(i).getSheetName());
			i++;
		}
		return StringUtils.join(existingSheetNames, ",");
	}

	
	

}
