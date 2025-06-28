package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelRead {
	
	//Data Members
	public static String [][] data;
	public static String filename;
	public static String sheetName;
	
	//Constructor
	public ExcelRead(String filename, String sheetName) {
		ExcelRead.filename = filename;
		ExcelRead.sheetName=sheetName;
	}
	
	//Reading Excel Sheet
	public static String [][] readExcelOperation() throws FileNotFoundException, IOException{
		XSSFWorkbook book = new XSSFWorkbook(new FileInputStream(filename));
		XSSFSheet sheet = book.getSheet(sheetName);
		DataFormatter format = new DataFormatter();
		int start_Row=2;
		int col_num = sheet.getRow(2).getLastCellNum();
		int row_num = sheet.getLastRowNum();
		
		int dataRowCount = 0;
		
	    for (int i = start_Row; i <= row_num; i++) {
	        if (sheet.getRow(i) != null) dataRowCount++;
	    }
	    
		data = new String[dataRowCount][col_num];
		System.out.println(row_num+" "+dataRowCount+" "+col_num);
		
		int dataIndex=0;
		for(int i = start_Row;i<=row_num;i++) {
			XSSFRow row = sheet.getRow(i);
			if(row==null) continue;
			for(int j = 0;j<col_num;j++) {
				XSSFCell cell = row.getCell(j);
				data[dataIndex][j] = (cell!=null)?format.formatCellValue(cell):"";
			}
			dataIndex++;
		}
		book.close();
		return data;
	}

	//Main Read
	@DataProvider(name="filereader")
	public static String[][] read() throws FileNotFoundException, IOException{
		return readExcelOperation();
	}
}
