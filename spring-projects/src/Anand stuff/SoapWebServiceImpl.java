package org.sample.demo.ws.impl;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.sample.demo.Customer;
import org.sample.demo.ws.SoapWebService;

@WebService(endpointInterface = "org.sample.demo.ws.SoapWebService")
public class SoapWebServiceImpl implements SoapWebService {

	@Override
	@WebMethod
	public Customer getMessage(Customer message) {
		System.out.println("message = " + message);
		return message;
	}

}
