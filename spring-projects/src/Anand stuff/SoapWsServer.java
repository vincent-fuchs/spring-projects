package org.sample.demo;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import javax.xml.ws.Endpoint;

public class SoapWsServer<S> {
	
	private String url;
	
	private S service; 
	
	private Endpoint endpoint;
	
	public SoapWsServer(String url, S service) {
		this.url = url; 
		this.service = service;
	}
	
	public void start() {
		endpoint = Endpoint.publish(url, service);
	    assertTrue(endpoint.isPublished());	 
	}

	public void stop() {
		endpoint.stop();
	    assertFalse(endpoint.isPublished());	 
	}

}
