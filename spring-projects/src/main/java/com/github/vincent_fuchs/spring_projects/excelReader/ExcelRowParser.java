package com.github.vincent_fuchs.spring_projects.excelReader;

import java.lang.reflect.Field;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelRowParser {
	
	public Object instanciateAndPopulateWithValuesFromRow(Class targetClass,Row rowToParse) throws InstantiationException, IllegalAccessException, SecurityException, NoSuchFieldException, IllegalArgumentException {
		
		Object targetBean=targetClass.newInstance();
		
		for(Field field : targetClass.getDeclaredFields())
		{
		     
		     if (field.isAnnotationPresent(ExcelColumn.class))
		     {
		    	 ExcelColumn excelColumn=field.getAnnotation(ExcelColumn.class);
		    	 
		    	 Cell cell=rowToParse.getCell(excelColumn.column());
		    	 ExcelCellDataParser parser=excelColumn.valueParser();
		    	 
		    	setField(targetBean,field.getName(),parser.getValue(cell));
		   
		     }
		}
			
		return targetBean;
	}
	
	
	private static void setField(Object object, String fieldName, Object value)
			throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Field field = object.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		field.set(object, value);
		field.setAccessible(false);
	}


	
}