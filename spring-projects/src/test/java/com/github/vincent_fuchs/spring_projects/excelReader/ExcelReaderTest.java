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

import com.github.vincent_fuchs.spring_projects.domain.Address;
import com.github.vincent_fuchs.spring_projects.domain.Customer;

public class ExcelReaderTest {
	
	private static final String CUSTOMERS_WORKSHEET_NAME = "customers";
	private static final String ADDRESSES_WORKSHEET_NAME = "addresses";
	private static final String SIMPLE_INPUT_FILE = "/com/github/vincent_fuchs/spring_projects/excelReader/simpleInputFile.xlsx";
	ExcelReader excelReader;
	
	List<WorksheetConfig> config;
	
	@Before
	public void initTest(){
		excelReader=new ExcelReader();
		excelReader.setInputFile(SIMPLE_INPUT_FILE);
		
		config=new ArrayList<WorksheetConfig>();
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
					
		excelReader.setWorsheetConfigs(buildWorksheetConfigsWithNames(CUSTOMERS_WORKSHEET_NAME));
		
		excelReader.init();		
	}
	
	
	@Test(expected=ExcelReaderConfigException.class)
	public void shouldThrowException_ifConfiguredWorksheetDoesntExist() throws Exception{
						
		excelReader.setWorsheetConfigs(buildWorksheetConfigsWithNames("someDummyNonExistingWorksheet"));		
		
		excelReader.init();		
		excelReader.readWorksheets();
	}
		
	
	@Test(expected=ExcelReaderConfigException.class)
	public void shouldThrowException_ifConfiguredWorksheetHasNoParser() throws Exception{
				
		excelReader.setWorsheetConfigs(buildWorksheetConfigsWithNames(CUSTOMERS_WORKSHEET_NAME));		
		
		excelReader.init();		
		excelReader.readWorksheets();
	}
	
	@Test
	public void shouldHaveAnEntryInResultWithSameNameAsWorksheetName() throws Exception{
	
		configureAndInitForSimpleInputFile();	
		
		Map<String, List<Object>> result=excelReader.readWorksheets();
		
		assertThat(result).isNotEmpty();
		assertThat(result).containsKey(CUSTOMERS_WORKSHEET_NAME);
	}

	
	@Test
	public void shouldHaveResultOfExpectedClassAfterParsing() throws Exception{

		configureAndInitForSimpleInputFile();		
		Map<String, List<Object>> result=excelReader.readWorksheets();
		
		List<Object> actualParsedCustomer=result.get(CUSTOMERS_WORKSHEET_NAME);
		
		assertThat(actualParsedCustomer).isNotEmpty();
		assertThat(actualParsedCustomer).hasSize(1);
		assertThat(actualParsedCustomer.get(0)).isInstanceOf(Customer.class);
	}
	

	@Test
	public void shouldHaveBeanWithExpectedValuesAfterParsing() throws Exception{

		configureAndInitForSimpleInputFile();		
		Map<String, List<Object>> result=excelReader.readWorksheets();
		
		List<Object> actualParsedCustomer=result.get(CUSTOMERS_WORKSHEET_NAME);
		
		Customer customer = (Customer)actualParsedCustomer.get(0);
		
		assertCustomerAttributes(customer);
	}
	
	
	@Test
	public void shouldParseCorrectlyWhenSeveralWorksheets() throws Exception{

		config.add(new WorksheetConfig(CUSTOMERS_WORKSHEET_NAME,Customer.class));
		config.add(new WorksheetConfig(ADDRESSES_WORKSHEET_NAME,Address.class));
		
		excelReader.setWorsheetConfigs(config);		
		
		excelReader.init();
				
		
		Map<String, List<Object>> result=excelReader.readWorksheets();
				
		
		List<Object> actualParsedCustomer=result.get(CUSTOMERS_WORKSHEET_NAME);
		
		Customer customer = (Customer)actualParsedCustomer.get(0);
		
		assertCustomerAttributes(customer);
		
		List<Object> actualParsedAddress=result.get(ADDRESSES_WORKSHEET_NAME);
		
		Address address = (Address)actualParsedAddress.get(0);
		
		assertThat(address.getCustomerId()).isEqualTo(1);
		assertThat(address.getNumber()).isEqualTo(42);
		assertThat(address.getStreet()).isEqualTo("expectedStreet");
		assertThat(address.getCountry()).isEqualTo("expectedCountry");
	}

	@Test
	public void shouldMergeWorksheetsContent() throws Exception{

		
		config.add(new WorksheetConfig(CUSTOMERS_WORKSHEET_NAME,Customer.class));
		config.add(new WorksheetConfig(ADDRESSES_WORKSHEET_NAME,Address.class));
		
		excelReader.setWorsheetConfigs(config);		
		
		excelReader.init();
				
		
		excelReader.readWorksheets();
		
		List<Object> mergedResult=excelReader.mergeWorksheets();
		assertThat(mergedResult).hasSize(1);
		assertCustomerAttributes((Customer)mergedResult.get(0));
		
		
	}
	

	private void assertCustomerAttributes(Customer customer) {
		assertThat(customer.getId()).isEqualTo(1);
		assertThat(customer.getFirstName()).isEqualTo("Vincent");
		assertThat(customer.getLastName()).isEqualTo("FUCHS");
		assertThat(customer.getAge()).isEqualTo(33);
	}
	
	private List<WorksheetConfig>  buildWorksheetConfigsWithNames(String... worksheetNames) {
		
		List<WorksheetConfig> config=new ArrayList<WorksheetConfig>();
		
		for(String worksheetName : worksheetNames){
			config.add(new WorksheetConfig(worksheetName));
		}
		
		return config;
	}
	
	private void configureAndInitForSimpleInputFile() throws IOException {
		config.add(new WorksheetConfig(CUSTOMERS_WORKSHEET_NAME,Customer.class));
				
		excelReader.setWorsheetConfigs(config);		
		
		excelReader.init();
	}
	
	

}
