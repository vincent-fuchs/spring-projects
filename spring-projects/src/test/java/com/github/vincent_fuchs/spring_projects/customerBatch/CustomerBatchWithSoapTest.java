package com.github.vincent_fuchs.spring_projects.customerBatch;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.vincent_fuchs.spring_projects.customerBatch.domain.Customer;
import com.github.vincent_fuchs.spring_projects.customerBatch.ws.soap.SoapCustomerWsClient;
import com.github.vincent_fuchs.spring_projects.customerBatch.ws.soap.SoapWebService;
import com.github.vincent_fuchs.spring_projects.customerBatch.ws.soap.SoapWebServiceImpl;
import com.github.vincent_fuchs.spring_projects.customerBatch.ws.soap.SoapWsServer;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {SoapCustomerWsClient.class,
										   TestSpecificConfiguration.class,
										   CustomerBatchConfiguration.class}
								)
//@WebIntegrationTest(randomPort=true)
//@WebIntegrationTest("server.port:8080")
@IntegrationTest({"spring.batch.job.enabled=false"})
public class CustomerBatchWithSoapTest {

//	@Value("${local.server.port}") 
//    public int targetWebServerPort;
	
		
	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;
	
	
	private SoapWebServiceImpl endpoint ;
	
	@Autowired
	private ConfigurableApplicationContext  appCtx ;
	
	@Autowired
    private ConfigurableEnvironment env;
	
	private SoapWsServer<SoapWebService> soapWsServer;
	

	
	@Before
	public void before() {
		
		endpoint= new SoapWebServiceImpl();
		
		soapWsServer=new SoapWsServer<SoapWebService>("http://localhost:8080/soapWebService", endpoint);
		soapWsServer.start();
			
	}
	
	@After
	public void after() throws MalformedURLException {
		soapWsServer.stop();
	}
	

	
	
	@Test
	public void targetShouldReceiveExpectedCustomer() throws Exception {

		
		JobExecution jobExecution = jobLauncherTestUtils.launchJob();

		//assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
		assertThat(endpoint.getReceivedCustomers()).hasSize(1);
		
		Customer actualCustomer=endpoint.getReceivedCustomers().get(0);
	//	assertThat(actualCustomer.getId()).isEqualTo(1);
		assertThat(actualCustomer.getFirstName()).isEqualTo("Vincent");
		assertThat(actualCustomer.getLastName()).isEqualTo("FUCHS");
	}

}
