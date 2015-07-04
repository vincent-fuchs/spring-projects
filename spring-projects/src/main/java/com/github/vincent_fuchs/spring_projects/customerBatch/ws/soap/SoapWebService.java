package com.github.vincent_fuchs.spring_projects.customerBatch.ws.soap;

import javax.jws.WebService;

import com.github.vincent_fuchs.spring_projects.customerws.domain.Customer;

@WebService
public interface SoapWebService {	
	public Customer getMessage(Customer customer);
}
