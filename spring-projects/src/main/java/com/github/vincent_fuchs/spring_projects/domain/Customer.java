package com.github.vincent_fuchs.spring_projects.domain;

import com.github.vincent_fuchs.spring_projects.excelReader.ExcelCellDataParser;
import com.github.vincent_fuchs.spring_projects.excelReader.ExcelColumn;

public class Customer {

	@ExcelColumn(column=0, excelColumnName = "Id", valueParser=ExcelCellDataParser.LONG)
	Long id;
	
	@ExcelColumn(column=1, excelColumnName = "first name")
	String firstName;
	
	@ExcelColumn(column=2, excelColumnName = "last name")
	String lastName;
	
	@ExcelColumn(column=3, excelColumnName = "age", valueParser=ExcelCellDataParser.INT)
	int age;
	
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
	
}
