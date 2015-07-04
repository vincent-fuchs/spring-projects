package com.github.vincent_fuchs.spring_projects.customerBatch.ws.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.github.vincent_fuchs.spring_projects.customerBatch.domain.Customer;
import com.github.vincent_fuchs.spring_projects.customerBatch.ws.CustomerWsClient;


public class RestCustomerWsClient implements CustomerWsClient {


	@Value("${target.host}")
	private String targetHost="undefined";
	
	@Value("${target.port}")
	private String targetPort="undefined";
	
	@Override
	public void sendCustomer(Customer customerFromExcel) {
				
		RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity("http://"+targetHost+":"+targetPort+"/integrate", customerFromExcel, null);
        
        System.out.println("sent client integration request");
		
		

	}

}
