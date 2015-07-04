package com.github.vincent_fuchs.spring_projects.customerBatch;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.EnvironmentTestUtils;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.vincent_fuchs.spring_projects.customerBatch.domain.Customer;
import com.github.vincent_fuchs.spring_projects.customerBatch.targetSystem.DummyCustomerController;
import com.github.vincent_fuchs.spring_projects.customerBatch.targetSystem.TargetRESTSystem;
import com.github.vincent_fuchs.spring_projects.customerBatch.ws.rest.RestCustomerWsClient;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RestCustomerWsClient.class,					   
										   TargetRESTSystem.class,
										   TestSpecificConfiguration.class,
										   CustomerBatchConfiguration.class}
								)
@WebIntegrationTest(randomPort=true)
//@WebIntegrationTest("server.port:8080")
@IntegrationTest({"spring.batch.job.enabled=false"})
public class CustomerBatchWithRestTest {

	@Value("${local.server.port}") 
    public int targetWebServerPort;
	
		
	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;
	
	@Autowired
	private DummyCustomerController endpoint ;
	
	@Autowired
	private ConfigurableApplicationContext  appCtx ;
	
	@Autowired
    private ConfigurableEnvironment env;

	
	@Before
	public void setDynamicTargetPortOnRestClient() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    
		//using reflection to bypass security and set the private field's value - workaround, but no other solution for now.  
	    RestCustomerWsClient restCustomerWsClient=(RestCustomerWsClient)appCtx.getBean(RestCustomerWsClient.class);
	    Field f = RestCustomerWsClient.class.getDeclaredField("targetPort");
        f.setAccessible(true);
        f.set(restCustomerWsClient,String.valueOf(targetWebServerPort));
	    
	}
	
	
	
	
	@Test
	public void targetShouldReceiveExpectedCustomer() throws Exception {

		
		JobExecution jobExecution = jobLauncherTestUtils.launchJob();

		//assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
		assertThat(endpoint.getReceivedCustomers()).hasSize(1);
		
		Customer actualCustomer=endpoint.getReceivedCustomers().get(0);
		assertThat(actualCustomer.getId()).isEqualTo(1);
		assertThat(actualCustomer.getFirstName()).isEqualTo("Vincent");
		assertThat(actualCustomer.getLastName()).isEqualTo("FUCHS");
	}

}
