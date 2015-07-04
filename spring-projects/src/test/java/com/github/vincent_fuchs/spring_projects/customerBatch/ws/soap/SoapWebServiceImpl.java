package com.github.vincent_fuchs.spring_projects.customerBatch.ws.soap;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import com.github.vincent_fuchs.spring_projects.customerBatch.targetSystem.DummyCustomerController;
import com.github.vincent_fuchs.spring_projects.customerws.domain.Customer;


@WebService(endpointInterface = "com.github.vincent_fuchs.spring_projects.customerBatch.ws.soap.SoapWebService")
public class SoapWebServiceImpl implements SoapWebService {

	List<Customer> receivedCustomers=new ArrayList<Customer>();
	

	@Override
	public Customer getMessage(Customer customer) {
		System.out.println("message = " + customer);
	
		receivedCustomers.add(customer);
		
		return customer;
	}
	
	public List<Customer> getReceivedCustomers() {
		return receivedCustomers;
	}

}
