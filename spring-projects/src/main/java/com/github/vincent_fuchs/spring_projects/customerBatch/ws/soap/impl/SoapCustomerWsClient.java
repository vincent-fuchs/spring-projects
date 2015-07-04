package com.github.vincent_fuchs.spring_projects.customerBatch.ws.soap.impl;

import java.net.MalformedURLException;

import com.github.vincent_fuchs.spring_projects.customerBatch.ws.CustomerWsClient;
import com.github.vincent_fuchs.spring_projects.customerws.domain.Customer;
import com.github.vincent_fuchs.spring_projects.customerBatch.ws.soap.SoapWebService;
import com.github.vincent_fuchs.spring_projects.customerBatch.ws.soap.WebServiceExecutorImpl;

public class SoapCustomerWsClient implements CustomerWsClient  {

	private GenericSoapWsClient<SoapWebService,Customer> soapWsClient;
	
	public SoapCustomerWsClient(){
			
		soapWsClient=new GenericSoapWsClient<SoapWebService,Customer>(SoapWebService.class);
		soapWsClient.setNamespace("http://soap.ws.customerBatch.spring_projects.vincent_fuchs.github.com/");
		soapWsClient.setWsdlUrlString("http://localhost:8080/soapWebService?wsdl");
		soapWsClient.setServicePart("SoapWebServiceImplService");
		soapWsClient.setPortPart("SoapWebServiceImplPort");
				
		soapWsClient.setWebServiceExecutor(new WebServiceExecutorImpl());
			
	}
	
	
	@Override
	public void sendCustomer(com.github.vincent_fuchs.spring_projects.customerBatch.domain.Customer customerFromExcel) {
			
		try {
		
			Customer webServiceCustomer = new Customer();
			webServiceCustomer.setAge(customerFromExcel.getAge());
			webServiceCustomer.setLastname(customerFromExcel.getLastName());
			webServiceCustomer.setFirstname(customerFromExcel.getFirstName());
			
			
			soapWsClient.execute(webServiceCustomer);
					
		} catch (MalformedURLException e) {
			System.out.println("issue while sending customer through SOAP call "+e.toString());
		}
		
		
	
	}
	
	
	
}
