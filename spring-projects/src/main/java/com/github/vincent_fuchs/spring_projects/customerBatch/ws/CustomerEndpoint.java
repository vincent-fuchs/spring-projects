package com.github.vincent_fuchs.spring_projects.customerBatch.ws;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import com.github.vincent_fuchs.spring_projects.customerBatch.domain.Customer;

@Endpoint
public class CustomerEndpoint {

	private static final String NAMESPACE_URI = "domain.customerWs.spring_projects.vincent_fuchs.github.com";
	

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "integrate")
	public boolean integrate(@RequestPayload Customer customer) {
		
		System.out.println("received request for customrt : "+customer.getLastName());
		
		
		return true;
	}
	
}
