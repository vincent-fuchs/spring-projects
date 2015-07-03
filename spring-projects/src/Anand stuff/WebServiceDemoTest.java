package org.sample.demo;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sample.demo.ws.SoapWebService;
import org.sample.demo.ws.impl.SoapWebServiceImpl;

public class WebServiceDemoTest {

	private SoapWsServer<SoapWebService> soapWsServer;
	
	private SoapWsClient<SoapWebService,Customer> soapWsClient;
	
	@Before
	public void before() {
		
		soapWsServer=new SoapWsServer<SoapWebService>("http://localhost:8080/soapWebService", new SoapWebServiceImpl());
		soapWsServer.start();
		
		soapWsClient=new SoapWsClient<SoapWebService,Customer>(SoapWebService.class);
		soapWsClient.setNamespace("http://impl.ws.demo.sample.org/");
		soapWsClient.setWsdlUrlString("http://localhost:8080/soapWebService?wsdl");
		soapWsClient.setServicePart("SoapWebServiceImplService");
		soapWsClient.setPortPart("SoapWebServiceImplPort");
		soapWsClient.setWebServiceExecutor(new WebServiceExecutorImpl());
	}
	
	@After
	public void after() throws MalformedURLException {
		soapWsServer.stop();
	}
	
	
	@Test
	public void testWebServiceCall() throws MalformedURLException {		
	    Customer customerIn = new Customer();
	    customerIn.setFirstName("fname");
	    customerIn.setLastName("lname");
	    customerIn.setId(1);
	    customerIn.setAddress(new Address());
	    Customer customerOut=soapWsClient.execute(customerIn);
	    
	    assertTrue("Message should match", customerOut.getId() == customerIn.getId());    
		
	}

}
