package com.github.vincent_fuchs.spring_projects.customerBatch;

import static org.assertj.core.api.Assertions.assertThat;

import javax.xml.ws.Endpoint;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.vincent_fuchs.spring_projects.customerBatch.ws.WebServiceConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CustomerBatchConfiguration.class, 
								 WebServiceConfiguration.class,
								 TestSpecificConfiguration.class})
@WebIntegrationTest(randomPort=true) 
public class CustomerBatchTest {

//	 @Value("${local.server.port}") 
//    int targetWebServerPort;
	
	
	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;
	
	private Endpoint endpoint ;
	
	
	@Before
	public void startupTargetWebService(){
		
//		endpoint= Endpoint.publish("http://localhost:8080/customerIntegrator", new CustomerIntegrator());
	//	assertThat(endpoint.isPublished()).isTrue();
	  
		
	}
	
	@After
	public void shutdownWebService(){
		
		if(endpoint!=null){
			endpoint.stop();
		}
		
	}
	
	@Test
	public void testLaunchJob() throws Exception {

		JobExecution jobExecution = jobLauncherTestUtils.launchJob();

		assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);

	}

}
