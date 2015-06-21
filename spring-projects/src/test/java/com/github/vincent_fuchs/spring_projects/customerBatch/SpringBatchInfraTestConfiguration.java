package com.github.vincent_fuchs.spring_projects.customerBatch;

import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBatchInfraTestConfiguration {

	@Bean
	public JobLauncherTestUtils jobLauncherTestUtils(){
		return new JobLauncherTestUtils();
	}	
	
}
