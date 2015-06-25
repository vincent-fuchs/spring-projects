package com.github.vincent_fuchs.spring_projects.customerBatch.targetSystem;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.vincent_fuchs.spring_projects.customerBatch.domain.Customer;


@RestController
public class RestCustomerController {

	public RestCustomerController(){
		System.out.println("CREATING controller");
	}
	
    private List<Customer> receivedCustomers=new ArrayList<Customer>();

    public List<Customer> getReceivedCustomers() {
		return receivedCustomers;
	}

	@RequestMapping(value = "/integrate", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void integrate(@RequestBody Customer customer) {
        receivedCustomers.add(customer);
    }

    // more controller methods
}