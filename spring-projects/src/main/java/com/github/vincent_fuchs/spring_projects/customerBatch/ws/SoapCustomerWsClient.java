package com.github.vincent_fuchs.spring_projects.customerBatch.ws;

import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.github.vincent_fuchs.spring_projects.customerBatch.domain.Customer;
import com.github.vincent_fuchs.spring_projects.customerws.domain.Integrate;

public class SoapCustomerWsClient extends WebServiceGatewaySupport implements CustomerWsClient  {

	@Override
	public void sendCustomer(Customer customerFromExcel){
		
		Jaxb2Marshaller marshaller=new Jaxb2Marshaller();
		marshaller.setSchema(new ClassPathResource("classpath:xsd/customer.xsd"));
		marshaller.setClassesToBeBound(com.github.vincent_fuchs.spring_projects.customerws.domain.Customer.class);
				
		setDefaultUri("http://localhost:8080/");
		setMarshaller(marshaller);
		
		com.github.vincent_fuchs.spring_projects.customerws.domain.Customer customerforWs=new com.github.vincent_fuchs.spring_projects.customerws.domain.Customer();
		
		customerforWs.setLastname(customerFromExcel.getLastName());
		customerforWs.setFirstname(customerFromExcel.getFirstName());
		
		Integrate request=new Integrate();
		request.setCustomer(customerforWs);
		
		getWebServiceTemplate().marshalSendAndReceive(request);
	
	}
	
	
	
}
