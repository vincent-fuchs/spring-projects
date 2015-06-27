package com.github.vincent_fuchs.spring_projects.customerBatch;

import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:com/github/vincent_fuchs/spring_projects/customerBatch/customerBatch-test.properties")
public class TestSpecificConfiguration {

	@Bean
	public JobLauncherTestUtils jobLauncherTestUtils(){
		return new JobLauncherTestUtils();
	}	

	
	
}
