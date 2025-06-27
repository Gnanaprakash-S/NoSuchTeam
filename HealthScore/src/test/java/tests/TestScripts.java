package tests;

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
	//priority=0,dataProvider="filereader",dataProviderClass=utils.ExcelRead.class
	@Test
	public void validateName() {
		homePage.nameField(" ");
	}
	
	@Test
	public void validateAge() {
		homePage.ageField("Dev","17");
	}

}


