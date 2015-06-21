package com.github.vincent_fuchs.spring_projects.customerBatch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.github.vincent_fuchs.spring_projects.customerBatch.domain.Address;
import com.github.vincent_fuchs.spring_projects.customerBatch.domain.Customer;
import com.github.vincent_fuchs.spring_projects.excelReader.WorksheetConfig;


@Configuration
@EnableBatchProcessing
public class CustomerBatchConfiguration {

	@Autowired
	private JobBuilderFactory jobs;
 
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		
		PropertySourcesPlaceholderConfigurer placeholder=new PropertySourcesPlaceholderConfigurer();
		
		placeholder.setIgnoreResourceNotFound(false);
		placeholder.setIgnoreUnresolvablePlaceholders(false);
		
		System.out.println("PLACEHOLDER CONFIG");
		
		
		return placeholder;
	}
	
	@Bean
	public Job addCustomerJob(){
		return jobs.get("addCustomerJob")
				.start(step())
				.build();
	}	
	
	@Bean
	public Step step(){
		return stepBuilderFactory.get("step")
				.<Customer,Customer>chunk(1)
				.reader(reader())
				.writer(writer())
				.build();
	}	

	@Bean
	public ItemReader<Customer> reader(){
		CustomerExcelReader reader = new CustomerExcelReader();
		
		List<WorksheetConfig> readerConfig=new ArrayList<WorksheetConfig>();
		readerConfig.add(new WorksheetConfig(CustomerExcelReader.CUSTOMERS_WORKSHEET_NAME,Customer.class));
		readerConfig.add(new WorksheetConfig(CustomerExcelReader.ADDRESSES_WORKSHEET_NAME,Address.class));
		
		reader.setWorsheetConfigs(readerConfig);	
		
		return reader; 
	}
	

    @Bean
    public ItemWriter<Customer> writer() {
    	return new CustomerWebServiceWriter();
    }

}
