package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage 
{
	WebDriver driver;

    @FindBy(id = "name")
    WebElement Name;
    
    @FindBy(id = "age")
    WebElement Age;
    
    @FindBy(id = "pulse")
    WebElement Pulse;
    

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
