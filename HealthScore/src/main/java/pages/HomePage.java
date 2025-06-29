package pages;


import org.openqa.selenium.Alert;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

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
    
    public void bpmField(String nameInput,String ageInput, String bpmInput) {
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

    	verify(pulse);

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
    
    public Boolean clickCalculate() {
    	name.clear();
    	age.clear();
    	Select sel = new Select(pulse);
    	sel.selectByIndex(0);
    	systolic.clear();
    	diastolic.clear();
    	confirm.click();
    	return verify(name);
    }

    public boolean isAlertPresent(String expectedText)
    {
    	try {
    		Alert alert=driver.switchTo().alert();
    		String text = alert.getText();
    		alert.accept();
    		return text.contains(expectedText);
    	}catch(NoAlertPresentException e){
    		return false;
    	}
    }
    
    public boolean isResultPresent()
    {
    	try {
    		return resultBox.isDisplayed();
    	}catch(NoAlertPresentException e){
    		return false;
    	}
    }



}
