package com.github.vincent_fuchs.spring_projects.excelReader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.github.vincent_fuchs.spring_projects.domain.Address;
import com.github.vincent_fuchs.spring_projects.domain.Customer;

public class CustomerExcelReader extends AbstractExcelReader implements ItemReader<Customer>{

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
