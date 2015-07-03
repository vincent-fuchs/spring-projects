package org.sample.demo;

import org.sample.demo.ws.SoapWebService;

public class WebServiceExecutorImpl implements WebServiceExecutor<SoapWebService, Customer> {

	@Override
	public Customer execute(SoapWebService service, Object... objects) {
		System.out.println("------------------- test --------------------");
		return service.getMessage((Customer)objects[0]);
	}

}
