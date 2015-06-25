package com.github.vincent_fuchs.spring_projects.customerBatch;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.vincent_fuchs.spring_projects.customerBatch.domain.Customer;
import com.github.vincent_fuchs.spring_projects.customerBatch.ws.CustomerWsClient;


public class CustomerWebServiceWriter implements ItemWriter<Customer> {

	@Autowired
	private CustomerWsClient wsClient;
	
	@Override
	public void write(List<? extends Customer> customersToWrite) throws Exception {
				
		for(Customer customer : customersToWrite){
			System.out.println("RECEIVED CUSTOMER TO WRITE : "+customer.getFirstName());
			wsClient.sendCustomer(customer);
			System.out.println("CUSTOMER SENT");
		}
	}

}
