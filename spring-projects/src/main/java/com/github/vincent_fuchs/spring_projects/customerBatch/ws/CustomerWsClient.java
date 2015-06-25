package com.github.vincent_fuchs.spring_projects.customerBatch.ws;

import com.github.vincent_fuchs.spring_projects.customerBatch.domain.Customer;

public interface CustomerWsClient {

	public abstract void sendCustomer(Customer customerFromExcel);

}