package com.github.vincent_fuchs.spring_projects.customerBatch;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.github.vincent_fuchs.spring_projects.customerBatch.targetSystem.TargetRESTSystem;
import com.github.vincent_fuchs.spring_projects.customerBatch.targetSystem.RestCustomerController;
import com.github.vincent_fuchs.spring_projects.customerBatch.domain.Customer;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {CustomerBatchConfiguration.class, 
										   TargetRESTSystem.class,
										   TestSpecificConfiguration.class})
@WebAppConfiguration
@IntegrationTest("server.port:8080") 
public class CustomerBatchWithRestTest {

	@Value("${local.server.port}") 
    int targetWebServerPort;
	
	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;
	
	@Autowired
	private RestCustomerController endpoint ;

	@Test
	public void targetShouldReceivedExpectedCustomer() throws Exception {

		JobExecution jobExecution = jobLauncherTestUtils.launchJob();

		assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
		assertThat(endpoint.getReceivedCustomers()).hasSize(1);
		
		Customer actualCustomer=endpoint.getReceivedCustomers().get(0);
		assertThat(actualCustomer.getId()).isEqualTo(1);
		assertThat(actualCustomer.getFirstName()).isEqualTo("Vincent");
		assertThat(actualCustomer.getLastName()).isEqualTo("FUCHS");
	}

}
