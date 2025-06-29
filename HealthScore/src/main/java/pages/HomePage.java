package pages;

import java.util.List;

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
    

	public Boolean verify(WebElement element) {

	public void verify(WebElement element, String value) 
	{

		JavascriptExecutor js = (JavascriptExecutor) driver;
    	Boolean isValid = (Boolean) js.executeScript("return arguments[0].checkValidity();", element);
    	return isValid;
	}

    public Boolean nameField(String input) {
    	name.clear();
    	name.sendKeys(input);
    	confirm.click();
    	
    	return verify(name);

    public void nameField(String input) 
    {
    	name.sendKeys(input);
    	confirm.click();
    	verify(name,input);

    }
    
    public Boolean ageField(String nameInput,String ageInput) {
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
    	System.out.println(bpmInput+" is present in the option List.");
    	sel.selectByVisibleText(bpmInput);
    	confirm.click();
    }
    
    public Boolean systolicBP(String nameInput,String ageInput, String bpmInput, String systolicBPInput) {
    	name.clear();
    	age.clear();
    	pulse.clear();
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
    	pulse.clear();
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
    public Boolean clickCalculate() {
    	name.clear();
    	age.clear();
    	Select sel = new Select(pulse);
    	sel.deselectAll();
    	pulse.clear();
    	systolic.clear();
    	diastolic.clear();
    	confirm.click();
    	return verify(name);
    }
    
    

}
