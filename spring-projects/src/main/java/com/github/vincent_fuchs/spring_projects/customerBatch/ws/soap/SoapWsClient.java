package com.github.vincent_fuchs.spring_projects.customerBatch.ws.soap;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class SoapWsClient<S, O> {
	
	private WebServiceExecutor<S, O> webServiceExecutor;
	
	private Class<S> serviceClass;
	
	private String wsdlUrlString;
	
	private String namespace;
	
	private String servicePart;
	
	private String portPart;
	
	public SoapWsClient(Class<S> serviceClass) {
		this.serviceClass=serviceClass;
	}
	
	public O execute(Object...objects) throws MalformedURLException {
		URL wsdlUrl = new URL(wsdlUrlString);
	    QName serviceQName = new QName(namespace, servicePart);
	    QName portQName = new QName(namespace, portPart);
		Service service = Service.create(wsdlUrl, serviceQName);
		S webService = service.getPort(portQName,serviceClass);
		return webServiceExecutor.execute(webService, objects);		
	}
	
	public void setServiceClass(Class<S> serviceClass) {
		this.serviceClass = serviceClass;
	}

	public void setWsdlUrlString(String wsdlUrlString) {
		this.wsdlUrlString = wsdlUrlString;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public void setServicePart(String servicePart) {
		this.servicePart = servicePart;
	}

	public void setPortPart(String portPart) {
		this.portPart = portPart;
	}

	public void setWebServiceExecutor(WebServiceExecutor<S, O> webServiceExecutor) {
		this.webServiceExecutor = webServiceExecutor;
	}	
}
