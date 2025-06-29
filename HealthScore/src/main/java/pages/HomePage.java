package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

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
    
	public boolean verify(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
    	Boolean isValid = (Boolean) js.executeScript("return arguments[0].checkValidity();", element);
    	return isValid;
	}

    public void nameField(String input) {
    	name.clear();
    	name.sendKeys(input);
    	confirm.click();
    }
    public boolean isNameValid(){
    	return verify(name);
    }
    
    public void ageField(String nameInput,String ageInput) {
    	name.sendKeys(nameInput);
    	age.sendKeys(ageInput);
    	confirm.click();
    	
    	verify(age);
    	
    }
    
    public void bpmField(String nameInput,String ageInput, String bpmInput) {
    	name.sendKeys(nameInput);
    	age.sendKeys(ageInput);
    	Select sel = new Select(pulse);
    	sel.selectByVisibleText(bpmInput);
    	confirm.click();
    	
    	verify(pulse);
    }
    
    public void systolicBP(String nameInput,String ageInput, String bpmInput, String systolicBPInput) {
    	name.sendKeys(nameInput);
    	age.sendKeys(ageInput);
    	Select sel = new Select(pulse);
    	sel.selectByVisibleText(bpmInput);
    	systolic.sendKeys(systolicBPInput);
    	confirm.click();
    	
    	verify(systolic);
    }
    
    public void diastolicBP(String nameInput,String ageInput, String bpmInput, String systolicBPInput, String diastolicBPInput) {
    	name.sendKeys(nameInput);
    	age.sendKeys(ageInput);
    	Select sel = new Select(pulse);
    	sel.selectByVisibleText(bpmInput);
    	systolic.sendKeys(systolicBPInput);
    	diastolic.sendKeys(diastolicBPInput);
    	confirm.click();
    	
    	verify(diastolic);
    	
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
    
    public boolean isResultPresent(String expectedText)
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


}
