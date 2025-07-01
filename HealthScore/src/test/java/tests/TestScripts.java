package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import ExtentListener.ExtentTestListener;
import base.BaseTest;
import pages.HomePage;
import utils.ExcelRead;

@Listeners(ExtentTestListener.class)
public class TestScripts extends BaseTest{
	
	HomePage homePage;
	

	@BeforeMethod   
	public void setUpPagesAndUtils()
	{
		homePage = new HomePage(driver);
	}
	
	public WebDriver getDriver() {
		// TODO Auto-generated method stub
		return this.driver;
	}

	@DataProvider(name="filereader")
	public static String[][] read() throws IOException {
		ExcelRead excelData = new ExcelRead(System.getProperty("user.dir") + "/src/Resources/Updated_Final_Tests 2 (3).xlsx", "Test",2,8);
	    return excelData.readExcelOperation();
	} 
	
	@DataProvider(name="filereadRemarks")
	public static String[][] readRemarks() throws IOException {
	    ExcelRead excelData = new ExcelRead(System.getProperty("user.dir") + "/src/Resources/Updated_Final_Tests 2 (3).xlsx", "Remarks",1);
	    return excelData.readExcelOperation();
	}

	@Test(priority=0,dataProvider="filereader")
	public void validateName(String ... data) {
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(homePage.nameField(data[0]),data[0]+" Name needs to pass but fails.");    	
    	softAssert.assertFalse(homePage.nameField(data[1]),data[1]+" Name needs to fails but pass.");
    	softAssert.assertAll();
	}
	
	@Test(priority=1,dataProvider="filereader")
	public void validateAge(String ... data) {
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(homePage.ageField(data[0],data[2]),data[2]+" Age needs to pass but fails.");    	
    	softAssert.assertFalse(homePage.ageField(data[0],data[3]),data[3]+" Age needs to fails but pass.");
    	softAssert.assertAll();
	}
	
	@Test(priority=2,dataProvider="filereader")
	public void validatePulse(String ... data) {
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(homePage.bpmField(data[0],data[2],data[4]),data[4]+" Pulse needs to pass but fails."); 
		softAssert.assertFalse(homePage.bpmField(data[0],data[2],"Select Pulse Range"),"Select Pulse Range"+" Pulse needs to fail but pass."); 
		softAssert.assertAll();
	}

	  
	@Test(priority=3,dataProvider="filereader")
	public void validateSystolic(String ... data) {
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(homePage.systolicBP(data[0],data[2],data[4],data[6]),data[6]+" SystolicBP needs to pass but fails.");    	
    	softAssert.assertFalse(homePage.systolicBP(data[0],data[2],data[4],data[7]),data[7]+" SystolicBP needs to fails but pass.");
    	softAssert.assertAll();
	}

	@Test(priority=4,dataProvider="filereader")
	public void validateDiastolic(String ... data) {
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(homePage.diastolicBP(data[0],data[2],data[4],data[6],data[8]),data[8]+" DiastolicBP needs to pass but fails.");    	
    	softAssert.assertFalse(homePage.diastolicBP(data[0],data[2],data[4],data[6],data[9]),data[9]+" DiastolicBP needs to fails but pass.");
    	softAssert.assertAll();
	}
	
	@Test(priority=5)
	public void validateCalculate() {
		Assert.assertFalse(homePage.calculateWithoutMandatory(),"It's accepting form without any values.");
    	System.out.println("It's not submitting without the mandatory fields as EXPECTED"); 
	} 
	
	@DataProvider(name="filereaderScore")
	public static String[][] readScore() throws IOException {
		ExcelRead excelData = new ExcelRead(System.getProperty("user.dir") + "/src/Resources/Updated_Final_Tests 2 (3).xlsx", "Test",2,15);
	    return excelData.readExcelOperation();
	}    
	
	@Test(priority=6,dataProvider="filereaderScore")
	public void validateScoresGenerated(String...data)
	{
		String name = data[0];
	    String age = data[2];
	    String pulse = data[5];
	    String systolicBP = data[6];
	    String diastolicBP = data[8];
	    String expectedAgeScore = data[10];
	    String expectedPulseScore =data[11];
	    String expectedBPScore =data[12];
	    String exectedOverAllScore=data[14];
 
	    homePage.withAllValid(name, age, pulse, systolicBP, diastolicBP);
		
		if(homePage.isAlertPresent("consult a doctor")||homePage.isAlertPresent("not in range"))
		{
			Assert.fail("Alert appeard check inputs");
		}
		else if(homePage.isResultPresent())
		{
			SoftAssert softAssert = new SoftAssert();
			String actualAgeScore = homePage.getAgeScore();
			String actualPulseScore = homePage.getPulseScore();
			String actualBPScore = homePage.getBpScore();
			String actualOverallScore = homePage.getOverallScore();
			
			softAssert.assertEquals(actualAgeScore, expectedAgeScore, "Age score mismatch for age :"+age);
			softAssert.assertEquals(actualPulseScore, expectedPulseScore, "Pulse score mismatch for pulse :"+pulse);
			softAssert.assertEquals(actualBPScore, expectedBPScore, "BP score mismatch for BP values :"+systolicBP+","+diastolicBP);
			softAssert.assertEquals(actualOverallScore, exectedOverAllScore, "Overall score mismatch for inputs :"+age+","+pulse+","+systolicBP+","+diastolicBP);
			softAssert.assertAll();
		}
		else
		{
			Assert.fail("Failing to provide score for inputs :"+age+","+pulse+","+systolicBP+","+diastolicBP);
		}
	}
	
	@Test(priority=7)
	public void alertConsultDoctor() throws FileNotFoundException, IOException
	{
		homePage.consultDoctor("Consult_Doctor");
	}
	
	@Test(priority=8)
	public void alertNotInRange() throws FileNotFoundException, IOException
	{
		homePage.notInRange("Not_In_Range");
	}
	

	@Test(priority=9)
	public void scoreCard() throws FileNotFoundException, IOException
	{
		homePage.success("Success");
	}
	
	
	@Test(priority=10, dataProvider="filereadRemarks")
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
	
	@Test(priority = 11)  
	public void validateResetButton(){
	    System.out.println("Testing Reset Button behavior");
	    homePage.withAllValid("Dhanush", "25", "60-80 BPM", "110", "70");
	    Assert.assertTrue(homePage.isResultPresent(),"Result card not displayed before reset.");
	    homePage.clickReset();
	    Assert.assertFalse(homePage.isResultPresent(), "Result card should be hidden after reset.");
	    Assert.assertTrue(homePage.areFieldsCleared(), "Input fields were not cleared after reset.");
	    System.out.println("Reset button successfully cleared all fields and hide the result.");
	}


}


