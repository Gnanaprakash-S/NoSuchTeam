package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage {
	
	WebDriver driver;
	
	@FindBy(id="name")
	WebElement name;
	
	@FindBy(id="age")
	WebElement age;
	
	@FindBy(xpath ="//button[@type=\"submit\"]")
	WebElement confirm;
	
	@FindBy(tagName="form")
	WebElement form;
	
	
	public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    

    public void nameField(String input) {
    	name.sendKeys(input);
    	confirm.click();
    	
    	
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	Boolean isValid = (Boolean) js.executeScript("return arguments[0].checkValidity();", name);
    	
    	if(!isValid)
    	Assert.assertTrue(isValid,"Invalid input was accepted for value : "+input);
    	
    	else
    		System.out.println("Valid input for value : "+input);
    }
    
    public void ageField(String nameInput,String ageInput) {
    	name.sendKeys(nameInput);
    	age.sendKeys(ageInput);
    	confirm.click();
    	
    	
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	Boolean isValid = (Boolean) js.executeScript("return arguments[0].checkValidity();", age);
    	
    	if(!isValid)
    	Assert.assertTrue(isValid,"Invalid input was accepted for value : "+ageInput);
    	
    	else
    		System.out.println("Valid input for value : "+ageInput);
    }

}
