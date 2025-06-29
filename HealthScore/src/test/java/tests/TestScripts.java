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

	public static String[][] read() throws IOException {
		ExcelRead excelData = new ExcelRead(System.getProperty("user.dir") + "/src/Resources/Updated_Final_Tests 2.xlsx", "Test",2);
	    return excelData.readExcelOperation();
	}

	@Test(priority=0,dataProvider="filereader")
	public void validateName(String ... data) {
		Assert.assertTrue(homePage.nameField(data[0]),data[0]+" it needs to pass but fails.");    	
    	Assert.assertFalse(homePage.nameField(data[1]),data[1]+" it needs to fails but pass.");
	}
	
	@Test(priority=1,dataProvider="filereader")
	public void validateAge(String ... data) {
		homePage.ageField(data[0],data[1]);
	}
	
	@Test(priority=2,dataProvider="filereader")
	public void validatePulse(String ... data) {
		homePage.bpmField("bilal","18","Below 40");
//		homePage.bpmField(data[0],data[1],data[2]);

	}
	
	@Test(priority=3,dataProvider="filereader")
	public void validateSystolic(String ... data) {
		homePage.systolicBP(data[0],data[1],data[2],data[3]);

	}


	@Test(priority=4,dataProvider="filereader")
	public void validateDiastolic(String ... data) {
		homePage.diastolicBP(data[0],data[1],data[2],data[3],data[4]);
	}
	
	@Test(priority=5)
	public void validateCalculate() {
		Assert.assertFalse(homePage.clickCalculate(),"It's accepting form without any values.");
    	System.out.println("It's not submitting without the mandatory fields as EXPECTED"); 
	} 
	
	     


}


