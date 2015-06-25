package com.github.vincent_fuchs.spring_projects.customerBatch.ws;

import org.springframework.web.client.RestTemplate;

import com.github.vincent_fuchs.spring_projects.customerBatch.domain.Customer;


public class RestCustomerWsClient implements CustomerWsClient {

	@Override
	public void sendCustomer(Customer customerFromExcel) {
				
		RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity("http://localhost:8080/integrate", customerFromExcel, null);
        
        System.out.println("sent client integration request");
		
		

	}

}
