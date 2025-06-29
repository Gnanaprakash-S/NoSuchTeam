package tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

	@DataProvider(name="filereader")
	public static String[][] read() throws IOException {
		ExcelRead excelData = new ExcelRead(System.getProperty("user.dir") + "/src/Resources/Updated_Final_Tests 2 (2).xlsx", "Test",2,8);
	    return excelData.readExcelOperation();
	}
	
	@DataProvider(name="fileread")
	public static String[][] reads() throws IOException {
	    ExcelRead excelData = new ExcelRead(System.getProperty("user.dir") + "/src/Resources/Updated_Final_Tests 2 (2).xlsx", "Remarks",1,15);
	    return excelData.readExcelOperation();
	}

	@Test(priority=0,dataProvider="filereader")
	public void validateName(String ... data) {
		Assert.assertTrue(homePage.nameField(data[0]),data[0]+" Name needs to pass but fails.");    	
    	Assert.assertFalse(homePage.nameField(data[1]),data[1]+" Name needs to fails but pass.");
	}
	
	@Test(priority=1,dataProvider="filereader")
	public void validateAge(String ... data) {
		Assert.assertTrue(homePage.ageField(data[0],data[2]),data[2]+" Age needs to pass but fails.");    	
    	Assert.assertFalse(homePage.ageField(data[0],data[3]),data[3]+" Age needs to fails but pass.");
	}
	
	@Test(priority=2,dataProvider="filereader")
	public void validatePulse(String ... data) {

		Assert.assertTrue(homePage.bpmField(data[0],data[2],data[4]),data[4]+" Pulse needs to pass but fails."); 
		Assert.assertFalse(homePage.bpmField(data[0],data[2],"Select Pulse Range"),"Select Pulse Range"+" Pulse needs to pass but fails."); 


	}
	  
	@Test(priority=3,dataProvider="filereader")
	public void validateSystolic(String ... data) {
		Assert.assertTrue(homePage.systolicBP(data[0],data[2],data[4],data[6]),data[6]+" SystolicBP needs to pass but fails.");    	
    	Assert.assertFalse(homePage.systolicBP(data[0],data[2],data[4],data[7]),data[7]+" SystolicBP needs to fails but pass.");

	}

	@Test(priority=4,dataProvider="filereader")
	public void validateDiastolic(String ... data) {
		Assert.assertTrue(homePage.diastolicBP(data[0],data[2],data[4],data[6],data[8]),data[8]+" DiastolicBP needs to pass but fails.");    	
    	Assert.assertFalse(homePage.diastolicBP(data[0],data[2],data[4],data[6],data[9]),data[9]+" DiastolicBP needs to fails but pass.");
	}
	
	@Test(priority=5)
	public void validateCalculate() {
		Assert.assertFalse(homePage.clickCalculate(),"It's accepting form without any values.");
    	System.out.println("It's not submitting without the mandatory fields as EXPECTED"); 
	} 
	
	@DataProvider(name="filereaderScore")
	public static String[][] readScore() throws IOException {
		ExcelRead excelData = new ExcelRead(System.getProperty("user.dir") + "/src/Resources/Updated_Final_Tests 2 (2).xlsx", "Test",2,15);
	    return excelData.readExcelOperation();
	}    
	
	@Test(priority=6,dataProvider="filereaderScore")
	public void validateScoresGenerated(String...data)
	{
		homePage.withAllValid(data[0],data[2],data[5],data[6],data[8]);
		
		if(homePage.isAlertPresent("consult a doctor")||homePage.isAlertPresent("not in range"))
		{
			Assert.fail("Alert appeard ");
		}
		else if(homePage.isResultPresent())
		{
			String actualAgeScore = homePage.getAgeScore();
			String actualPulseScore = homePage.getPulseScore();
			String actualBPScore = homePage.getBpScore();
			String actualOverallScore = homePage.getOverallScore();
			
			Assert.assertEquals(actualAgeScore, data[10], "Age score mismatch");
			Assert.assertEquals(actualPulseScore, data[11], "Pulse score mismatch");
			Assert.assertEquals(actualBPScore, data[12], "BP score mismatch");
			Assert.assertEquals(actualOverallScore, data[14], "Overall score mismatch");

		}
		else
		{
			Assert.fail("It should provide score but it failed");
		}
		
	}
	
	@Test(priority=6, dataProvider="fileread")
	public void validateHealthRemark(String... data) {
	    String name = data[0];
	    String age = data[1];
	    String pulse = data[2];
	    String systolicBP = data[3];
	    String diastolicBP = data[4];
	    String expectedRemark = data[5];
 
	    homePage.withAllValid(name, age, pulse, systolicBP, diastolicBP);
	    Assert.assertTrue(homePage.isResultPresent(), "Result card not displayed for " + name);
	    
	    WebElement resultBox = driver.findElement(By.className("result-text-box"));
	    String resultText = resultBox.getText();
	    Assert.assertTrue(resultText.toUpperCase().contains(expectedRemark.toUpperCase()),"Mismatch for " + name + ": Expected '" + expectedRemark + "', but found -> " + resultText);
	}
	
	
	
	
	@Test(priority = 7)  
	public void validateResetButton(){
	    System.out.println("Testing Reset Button behavior");
	    homePage.withAllValid("Dhanush", "25", "60-80 BPM", "110", "70");
	    Assert.assertTrue(homePage.isResultPresent(), "Result card not displayed before reset.");
	    homePage.clickReset();
	    Assert.assertTrue(homePage.isResultPresent(), "Result card should be hidden after reset.");
	    Assert.assertTrue(homePage.areFieldsCleared(), "Input fields were not cleared after reset.");
	    System.out.println("Reset button successfully cleared all fields and hid the result.");
	}


}


