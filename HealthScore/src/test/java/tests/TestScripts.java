package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import utils.ExcelRead;


public class TestScripts extends BaseTest{
	
	HomePage homePage;

	@BeforeMethod
	public void setUpPagesAndUtils()
	{
		homePage = new HomePage(driver);
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
	@DataProvider(name="filereader")
	public static String[][] read() throws FileNotFoundException, IOException{
		ExcelRead excelData= new ExcelRead("src/Resources/Health score calculator.xlsx","test_data_sheet");
		return excelData.readExcelOperation();
	}
	
	@Test(priority=0,dataProvider="filereader")
	public void validateNameField(String...data){
		homePage.nameField(data[0]);
		Assert.assertTrue(homePage.isNameValid(),"Invalid input was not accepted : "+data[0]);
    	//System.out.println("Valid input for value : "+data[0]);
		homePage.nameField(data[1]);
    	Assert.assertFalse(homePage.isNameValid(),"Invalid input was accepted  : "+data[1]);
    	//System.out.println("Invalid input for value : "+data[1]);
	}
	
	@Test
	public void validateAgeField() {
		homePage.ageField("Dev","17");
	}

}


