package org.sample.demo.ws;

import javax.jws.WebService;

import org.sample.demo.Customer;

@WebService
public interface SoapWebService {	
	public Customer getMessage(Customer customer);
}
