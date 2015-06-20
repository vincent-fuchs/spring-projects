package com.github.vincent_fuchs.spring_projects.excelReader;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class WorksheetConfigTest {

	@Test
	public void shouldReturnExpectedCoordinatesForConfiguredFirstCellWithData_default(){
		
		WorksheetConfig wsConfig=new WorksheetConfig("dummy",Object.class);
		
		assertThat(wsConfig.getFirstCellWithDataRowIndex()).isEqualTo(1);		
		assertThat(wsConfig.getFirstCellWithDataColumnIndex()).isEqualTo(0);
	}
	
	@Test
	public void shouldReturnExpectedCoordinatesForConfiguredFirstCellWithData_notDefault(){
		
		WorksheetConfig wsConfig=new WorksheetConfig("dummy",Object.class);
		
		wsConfig.setFirstCellWithData("C4");
		
		assertThat(wsConfig.getFirstCellWithDataRowIndex()).isEqualTo(3);		
		assertThat(wsConfig.getFirstCellWithDataColumnIndex()).isEqualTo(2);
	}

}
