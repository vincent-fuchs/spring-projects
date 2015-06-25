package com.github.vincent_fuchs.spring_projects.customerBatch.targetSystem;

import java.util.List;

import com.github.vincent_fuchs.spring_projects.customerBatch.domain.Customer;

public interface DummyCustomerController {

	public abstract List<Customer> getReceivedCustomers();

	
	
}
