package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import utils.ExcelRead;


public class TestScripts extends BaseTest{
	
	HomePage homePage;
	ExcelRead excelData;

	@BeforeMethod
	public void setUpPagesAndUtils()
	{
		homePage = new HomePage(driver);
		excelData= new ExcelRead("src/Resources/Health score calculator.xlsx","test_data_sheet");
	}
	//(priority=0,dataProvider="filereader",dataProviderClass=utils.ExcelRead.class)
//	@Test
//	public void getData() throws FileNotFoundException, IOException {
//		String[][] data = ExcelRead.read();
//		
//		for(String[] val:data)
//		{
//			for(String ele:val)
//			{
//				System.out.print(ele+" ");
//			}
//			System.out.println();
//		}
//	}
	@Test(priority=0,dataProvider="filereader",dataProviderClass=utils.ExcelRead.class)
	public void validateName(String validName,String invalidName,String validAge,String invalidAge) {
		homePage.nameField(validName);
	}
	
	@Test
	public void validateAge() {
		homePage.ageField("Dev","17");
	}

}


