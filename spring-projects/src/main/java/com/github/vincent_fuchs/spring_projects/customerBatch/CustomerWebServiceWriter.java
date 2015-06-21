package com.github.vincent_fuchs.spring_projects.customerBatch;
import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.github.vincent_fuchs.spring_projects.customerBatch.domain.Customer;


public class CustomerWebServiceWriter implements ItemWriter<Customer> {

	@Override
	public void write(List<? extends Customer> arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
