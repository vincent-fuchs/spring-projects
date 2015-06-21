package com.github.vincent_fuchs.spring_projects.domain;

import com.github.vincent_fuchs.spring_projects.excelReader.ExcelCellDataParser;
import com.github.vincent_fuchs.spring_projects.excelReader.ExcelColumn;

public class Address {
	
	@ExcelColumn(column=0, valueParser=ExcelCellDataParser.LONG)
	private long customerId;
	
	@ExcelColumn(column=1, valueParser=ExcelCellDataParser.INT)
	private int number;
	
	@ExcelColumn(column=2)
	private String street;
	
	@ExcelColumn(column=3)
	private String city;
	
	@ExcelColumn(column=4)
	private String country;

	public long getCustomerId() {
		return customerId;
	}

	public int getNumber() {
		return number;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}


}
