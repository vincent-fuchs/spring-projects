package com.github.vincent_fuchs.spring_projects.customerBatch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.github.vincent_fuchs.spring_projects.customerBatch.domain.Address;
import com.github.vincent_fuchs.spring_projects.customerBatch.domain.Customer;
import com.github.vincent_fuchs.spring_projects.excelReader.AbstractExcelReader;

public class CustomerExcelReader extends AbstractExcelReader implements ItemReader<Customer>{

	public static final String CUSTOMERS_WORKSHEET_NAME = "customers";
	public static final String ADDRESSES_WORKSHEET_NAME = "addresses";

	@Override
	public Customer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Object> mergeWorksheets() {
		
		List<Object> mergedResult=new ArrayList<Object>();
		
		List<Object> parsedCustomers=parsedResultFromWorksheets.get("customers");
		
		List<Object> parsedAddresses=parsedResultFromWorksheets.get("addresses");

		for(Object customerAsObj : parsedCustomers){
			
			Customer customer=(Customer)customerAsObj;
			
			for(Object addressAsObj : parsedAddresses){
				
				Address address = (Address)addressAsObj;
				
				if(address.getCustomerId()==customer.getId()){
					customer.setAddress(address);
					break;
				}
			}
			
			
			mergedResult.add(customer);
			
		}
		
		
		return mergedResult;
		
	}

}
