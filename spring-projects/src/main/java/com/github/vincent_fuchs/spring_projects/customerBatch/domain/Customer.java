package com.github.vincent_fuchs.spring_projects.customerBatch.domain;

import com.github.vincent_fuchs.spring_projects.excelReader.ExcelCellDataParser;
import com.github.vincent_fuchs.spring_projects.excelReader.ExcelColumn;

public class Customer {

	@ExcelColumn(column=0, valueParser=ExcelCellDataParser.LONG)
	Long id;
	
	@ExcelColumn(column=1)
	String firstName;
	
	@ExcelColumn(column=2)
	String lastName;
	
	@ExcelColumn(column=3, valueParser=ExcelCellDataParser.INT)
	int age;
	
	Address address;
	
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getAge() {
		return age;
	}
	
	
	public static Customer toBatchDomainCustomer(com.github.vincent_fuchs.spring_projects.customerws.domain.Customer webServiceCustomer){
		
		Customer newCustomer=new Customer();
		
		newCustomer.firstName=webServiceCustomer.getFirstname();
		newCustomer.lastName=webServiceCustomer.getLastname();
		newCustomer.age=webServiceCustomer.getAge();
				
		return newCustomer;
	}
	
}
