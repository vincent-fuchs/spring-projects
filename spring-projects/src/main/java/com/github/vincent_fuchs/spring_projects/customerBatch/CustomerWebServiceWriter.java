package com.github.vincent_fuchs.spring_projects.customerBatch;
import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.github.vincent_fuchs.spring_projects.customerBatch.domain.Customer;


public class CustomerWebServiceWriter implements ItemWriter<Customer> {

	@Override
	public void write(List<? extends Customer> customersToWrite) throws Exception {
		
		for(Customer customer : customersToWrite){
			System.out.println("RECEIVED CUSTOMER TO WRITE : "+customer.getFirstName());
		}
	}

}
