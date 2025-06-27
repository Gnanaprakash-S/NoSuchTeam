package pages;

import org.openqa.selenium.JavascriptExecutor;
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
	
	public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
	public void verify(WebElement element, String value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
    	Boolean isValid = (Boolean) js.executeScript("return arguments[0].checkValidity();", element);
    	
    	if(!isValid)
    		Assert.assertTrue(isValid,"Invalid input was accepted for value : "+value);
    	
    	else
    		System.out.println("Valid input for value : "+value);
	}

    public void nameField(String input) {
    	name.sendKeys(input);
    	confirm.click();
    	
    	verify(name,input);
    }
    
    public void ageField(String nameInput,String ageInput) {
    	name.sendKeys(nameInput);
    	age.sendKeys(ageInput);
    	confirm.click();
    	
    	verify(age,ageInput);
    	
    }
    
    public void bpmField(String nameInput,String ageInput, String bpmInput) {
    	name.sendKeys(nameInput);
    	age.sendKeys(ageInput);
    	Select sel = new Select(pulse);
    	sel.selectByVisibleText(bpmInput);
    	confirm.click();
    	
    	verify(pulse,bpmInput);
    }
    
    public void systolicBP(String nameInput,String ageInput, String bpmInput, String systolicBPInput) {
    	name.sendKeys(nameInput);
    	age.sendKeys(ageInput);
    	Select sel = new Select(pulse);
    	sel.selectByVisibleText(bpmInput);
    	systolic.sendKeys(systolicBPInput);
    	confirm.click();
    	
    	verify(systolic,systolicBPInput);
    }
    
    public void diastolicBP(String nameInput,String ageInput, String bpmInput, String systolicBPInput, String diastolicBPInput) {
    	name.sendKeys(nameInput);
    	age.sendKeys(ageInput);
    	Select sel = new Select(pulse);
    	sel.selectByVisibleText(bpmInput);
    	systolic.sendKeys(systolicBPInput);
    	diastolic.sendKeys(diastolicBPInput);
    	confirm.click();
    	
    	verify(diastolic,diastolicBPInput);
    	
    }

}
