package com.github.vincent_fuchs.spring_projects.customerBatch.ws.soap.impl;

import java.net.MalformedURLException;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

import com.github.vincent_fuchs.spring_projects.customerBatch.ws.CustomerWsClient;
import com.github.vincent_fuchs.spring_projects.customerws.domain.Customer;
import com.github.vincent_fuchs.spring_projects.customerBatch.ws.soap.SoapWebService;
import com.github.vincent_fuchs.spring_projects.customerBatch.ws.soap.WebServiceExecutorImpl;

public class SoapCustomerWsClient implements CustomerWsClient, InitializingBean  {

	@Value("${target.namespace}")
	private String targetNameSpace;
	
	@Value("${target.wsdlUrl}")
	private String wsdlUrl;
	
	@Value("${target.service.part}")
	private String targetService;
		
	@Value("${target.port.part}")
	private String targetPort;
	
	private GenericSoapWsClient<SoapWebService,Customer> soapWsClient;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		soapWsClient=new GenericSoapWsClient<SoapWebService,Customer>(SoapWebService.class);
		soapWsClient.setNamespace(targetNameSpace);
		soapWsClient.setWsdlUrlString(wsdlUrl);
		soapWsClient.setServicePart(targetService);
		soapWsClient.setPortPart(targetPort);
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
			e.printStackTrace();
		}
	
	}


	public void setTargetNameSpace(String targetNameSpace) {
		this.targetNameSpace = targetNameSpace;
	}


	public void setWsdlUrl(String wsdlUrl) {
		this.wsdlUrl = wsdlUrl;
	}


	public void setTargetService(String targetService) {
		this.targetService = targetService;
	}


	public void setTargetPort(String targetPort) {
		this.targetPort = targetPort;
	}



	
}
