package com.github.vincent_fuchs.spring_projects.excelReader;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumn {

	int column();

	String excelColumnName();

	ExcelCellDataParser valueParser() default ExcelCellDataParser.STRING;

}