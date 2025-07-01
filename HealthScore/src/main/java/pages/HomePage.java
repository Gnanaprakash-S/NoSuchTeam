package pages;


import org.openqa.selenium.Alert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import utils.ExcelRead;

public class HomePage {
	
	WebDriver driver;
	  
	@FindBy(id="name")
	WebElement name;
	
	@FindBy(id="age")
	WebElement age;
	
	@FindBy(id="pulse")
	WebElement pulse;
	
	@FindBy(id="bp-systolic")
	WebElement systolic;
	
	@FindBy(id="bp-diastolic")
	WebElement diastolic;
	
	@FindBy(xpath ="//button[@type=\"submit\"]")
	WebElement confirm;
	
	@FindBy(id="scoreCard")
	WebElement resultBox;
	
	@FindBy(id="age_score")
	WebElement ageScore;
	
	@FindBy(id="pulse_score")
	WebElement pulseScore;
	
	@FindBy(id="bp_score")
	WebElement bpScore;
	
	@FindBy(id="overall_score")
	WebElement overallScore;
	
	@FindBy(xpath="//button[@class=\"btn btn-reset\"]")
	WebElement reset;
	
	public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    


	public Boolean verify(WebElement element) 
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
    	Boolean isValid = (Boolean) js.executeScript("return arguments[0].checkValidity();", element);
    	return isValid;
	}


    public Boolean nameField(String input) {

    	name.clear();
    	name.sendKeys(input);
    	return verify(name);

    }

    public Boolean ageField(String nameInput,String ageInput) {
    	name.clear();
    	age.clear();
    	name.sendKeys(nameInput);
    	age.sendKeys(ageInput);
    	confirm.click();
    	return verify(age);     	
    }
    
    public Boolean bpmField(String nameInput,String ageInput, String bpmInput) {
    	name.clear();
    	age.clear();
    	name.sendKeys(nameInput);
    	age.sendKeys(ageInput);
    	boolean found=false;
    	Select sel = new Select(pulse);
    	List<WebElement> options= sel.getOptions();
    	for(WebElement ele : options) {
    		if(ele.getText().trim().equals(bpmInput)) {
    			found=true;
    			break;
    		}
    	}
    	Assert.assertTrue(found,bpmInput+" is not present in the option List.");
    	sel.selectByVisibleText(bpmInput);
    	confirm.click();

    	return verify(pulse);

    }
    
    public Boolean systolicBP(String nameInput,String ageInput, String bpmInput, String systolicBPInput) {
    	name.clear();
    	age.clear();
    	systolic.clear();
    	name.sendKeys(nameInput);
    	age.sendKeys(ageInput);
    	Select sel = new Select(pulse);
    	sel.selectByVisibleText(bpmInput);
    	systolic.sendKeys(systolicBPInput);
    	confirm.click();
    	
    	return verify(systolic);
    }
    
    public Boolean diastolicBP(String nameInput,String ageInput, String bpmInput, String systolicBPInput, String diastolicBPInput) {
    	name.clear();
    	age.clear();
    	systolic.clear();
    	diastolic.clear();
    	name.sendKeys(nameInput);
    	age.sendKeys(ageInput);
    	Select sel = new Select(pulse);
    	sel.selectByVisibleText(bpmInput);
    	systolic.sendKeys(systolicBPInput);
    	diastolic.sendKeys(diastolicBPInput);
    	confirm.click();

    	return verify(diastolic);

    }


    public void withAllValid(String nameInput,String ageInput, String bpmInput, String systolicBPInput, String diastolicBPInput) {
    	clickReset();
    	name.clear();
    	age.clear();
    	systolic.clear();
    	diastolic.clear();
    	name.sendKeys(nameInput);
    	age.sendKeys(ageInput);
    	Select sel = new Select(pulse);
    	sel.selectByVisibleText(bpmInput);
    	systolic.sendKeys(systolicBPInput);
    	diastolic.sendKeys(diastolicBPInput);
    	confirm.click();
    }
  
    public Boolean calculateWithoutMandatory() {
    	name.clear();
    	age.clear();
    	Select sel = new Select(pulse);
    	sel.selectByIndex(0);
    	systolic.clear();
    	diastolic.clear();
    	confirm.click();
    	return verify(name);
    }
    
    public String  getAgeScore()
    {
    	return ageScore.getText();
    }
    
    public String getPulseScore()
    {
    	return pulseScore.getText();
    }
    
    public String getBpScore()
    {
    	return bpScore.getText();
    }
    
    public String getOverallScore()
    {
    	return overallScore.getText();
    }

    public boolean isAlertPresent(String expectedText)
    {
    	try {
    		Alert alert=driver.switchTo().alert();
    		String text = alert.getText().toLowerCase();
    		alert.accept();
    		return text.contains(expectedText.toLowerCase());
    	}catch(NoAlertPresentException e){
    		return false;
    	}
    }
    public boolean isResultPresent()
    {
    	try {
    		return resultBox.isDisplayed();
    	}
    	catch(NoSuchElementException e)
    	{
    		return false;
    	}
    }
   

    public String[][] getTestData(String sheetName) throws FileNotFoundException, IOException 
    {
    	ExcelRead excelData = new ExcelRead(System.getProperty("user.dir") + "/src/Resources/Updated_Final_Tests 2 (3).xlsx", sheetName,1);
	    return excelData.readExcelOperation();
    }
    
    public void consultDoctor(String sheetName) throws FileNotFoundException, IOException
    {
    	String[][] data = getTestData(sheetName);
        int row = data.length;

	    for(int i=0;i<row;i++)
	    {
	    	withAllValid("Prakash", "18", data[i][3],data[i][0],data[i][1]);	
        	if(!isAlertPresent("Consult a Doctor"))
        	{
        		Assert.fail("Systolic BP : "+data[i][0]+"  Diastolic BP : "+data[i][1]+"  Pulse BP :"+data[i][3]+"Alert not generated to consult a doctor");
        		break;
        	}
	    }
    }
    
    public void notInRange(String sheetName) throws FileNotFoundException, IOException 
     { 
	    String[][] data = getTestData(sheetName);
        int row = data.length;

	    for(int i=0;i<row;i++)
	    {   
	    	withAllValid("Prakash", "18", data[i][3],data[i][0],data[i][1]);		
        	if(!isAlertPresent("Not Valid"))
        	{
        		Assert.fail("Systolic BP : "+data[i][0]+"  Diastolic BP : "+data[i][1]+"  Pulse BP :"+data[i][3]+" Alert not generated for BP values not in range");
        		break;
        	}
	    }
    }
    
    public void success(String sheetName) throws FileNotFoundException, IOException 
    {
	    String[][] data = getTestData(sheetName);
        int row = data.length;

	    for(int i=0;i<row;i++)
	    {
	    	withAllValid("Prakash", "18", data[i][3],data[i][0],data[i][1]);	
        	if(isResultPresent())
        	{
        		clickReset();
        	}
        	else
        	{
        		Assert.fail("Systolic BP : "+data[i][0]+"  Diastolic BP : "+data[i][1]+"  Pulse BP :"+data[i][3]+" result not generated alert interrupted");
        		break;
        	}
	    }
    }

    public void clickReset() {
    	if(reset.isDisplayed())
        reset.click();
    }
    
    @SuppressWarnings("deprecation")
	public String getFieldValue(WebElement element) {
        return element.getAttribute("value").trim();
    }
    
    public boolean areFieldsCleared() {
        return getFieldValue(name).isEmpty() && getFieldValue(age).isEmpty() &&
               new Select(pulse).getFirstSelectedOption().getText().contains("Select") &&
               getFieldValue(systolic).isEmpty() &&
               getFieldValue(diastolic).isEmpty();
    }
 

}


