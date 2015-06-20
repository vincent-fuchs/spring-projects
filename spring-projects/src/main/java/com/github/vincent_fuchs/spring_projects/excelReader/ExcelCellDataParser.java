package com.github.vincent_fuchs.spring_projects.excelReader;

import org.apache.poi.ss.usermodel.Cell;

public enum ExcelCellDataParser {
	
	STRING {

		@Override
		public Object getValue(Cell cell) {

			return cell.getStringCellValue();
		}

	},
	
	LONG {
		@Override
		public Object getValue(Cell cell) {
			Double cellValue=cell.getNumericCellValue();

			return cellValue.longValue();
		}
	},
	
	INT {

		public Object getValue(Cell cell) {
			
			Double cellValue=cell.getNumericCellValue();

			return cellValue.intValue();
		}
	};

	public abstract Object getValue(Cell cell);
}
