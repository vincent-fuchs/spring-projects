package com.github.vincent_fuchs.spring_projects.customerBatch.ws.soap;

import com.github.vincent_fuchs.spring_projects.customerws.domain.Customer;

public class WebServiceExecutorImpl implements WebServiceExecutor<SoapWebService, Customer> {

	@Override
	public Customer execute(SoapWebService service, Object... objects) {
		System.out.println("------------------- test --------------------");
		return service.getMessage((Customer)objects[0]);
	}

}
