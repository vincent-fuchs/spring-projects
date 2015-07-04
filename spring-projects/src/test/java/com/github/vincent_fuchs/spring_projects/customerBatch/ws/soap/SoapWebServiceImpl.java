package com.github.vincent_fuchs.spring_projects.customerBatch.ws.soap;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import com.github.vincent_fuchs.spring_projects.customerBatch.targetSystem.DummyCustomerController;
import com.github.vincent_fuchs.spring_projects.customerws.domain.Customer;


@WebService(endpointInterface = "com.github.vincent_fuchs.spring_projects.customerBatch.ws.soap.SoapWebService")
public class SoapWebServiceImpl implements SoapWebService,DummyCustomerController {

	List<com.github.vincent_fuchs.spring_projects.customerBatch.domain.Customer> receivedCustomers=new ArrayList<com.github.vincent_fuchs.spring_projects.customerBatch.domain.Customer>();
	

	@Override
	public Customer getMessage(Customer customer) {
		System.out.println("message = " + customer);
	
		receivedCustomers.add(com.github.vincent_fuchs.spring_projects.customerBatch.domain.Customer.toBatchDomainCustomer(customer));
		
		return customer;
	}
	
	

	@Override
	public List<com.github.vincent_fuchs.spring_projects.customerBatch.domain.Customer> getReceivedCustomers() {
		return receivedCustomers;
	}

}
