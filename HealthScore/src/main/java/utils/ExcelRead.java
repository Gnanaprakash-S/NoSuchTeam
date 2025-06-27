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
	public String [][] data;
	public String filename;
	public String sheetName;
	
	//Constructor
	public ExcelRead(String filename, String sheetName) {
		this.filename = filename;
		this.sheetName=sheetName;
		
	}
	
	//Reading Excel Sheet
	public String [][] readExcelOperation() throws FileNotFoundException, IOException{
		XSSFWorkbook book = new XSSFWorkbook(new FileInputStream(filename));
		XSSFSheet sheet = book.getSheet(sheetName);
		int col_num = sheet.getRow(0).getLastCellNum();
		int row_num = sheet.getLastRowNum();
		data = new String[row_num][col_num];
		DataFormatter format = new DataFormatter();
		for(int i = 1;i<= row_num;i++) {
			XSSFRow row = sheet.getRow(i);
			for(int j = 0;j<col_num;j++) {
				XSSFCell cell = row.getCell(j);
				data[i-1][j] = format.formatCellValue(cell);
			}
		}
		book.close();
		return data;
	}
	
	//Main Read
	@DataProvider(name="filereader")
	public String[][] read() throws FileNotFoundException, IOException{
		return readExcelOperation();
	}
}
