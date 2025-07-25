package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelRead {
	
	//Data Members
	public String [][] data;
	public String filename;
	public String sheetName;
	public int start_Row;
	public int row_End=-1;

	//Constructor
	public ExcelRead(String filename, String sheetName, int start_Row, int row_End) {
		this.filename = filename;
		this.sheetName=sheetName;
		this.start_Row=start_Row;
		this.row_End=row_End;
	}
	public ExcelRead(String filename, String sheetName, int start_Row) {
		this.filename = filename;
		this.sheetName=sheetName;
		this.start_Row=start_Row;
	}
	
	//Reading Excel Sheet
	public String [][] readExcelOperation() throws FileNotFoundException, IOException{
		XSSFWorkbook book = new XSSFWorkbook(new FileInputStream(filename));
		XSSFSheet sheet = book.getSheet(sheetName);
		DataFormatter format = new DataFormatter();
		int col_num = sheet.getRow(1).getLastCellNum();
		int dataRowCount = 0;
		
		if(row_End==-1)
		{
			row_End=sheet.getPhysicalNumberOfRows();
		}

	    for (int i = start_Row; i <= row_End; i++) {
	        if (sheet.getRow(i) != null) dataRowCount++;
	    }
	    
		data = new String[dataRowCount][col_num];
		
		int dataIndex=0;

		for(int i = start_Row;i<=row_End;i++) {
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

}
