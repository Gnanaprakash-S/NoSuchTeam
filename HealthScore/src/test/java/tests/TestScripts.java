package tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.MainPage;


public class TestScripts 
{

	  WebDriver driver;
    MainPage mainPage;

    @BeforeTest
    @Parameters("browser")
    public void setUp(String browser) {
    	if(browser.equalsIgnoreCase("chrome")) {
    		driver = new ChromeDriver();
    	}else if(browser.equalsIgnoreCase("edge")) {
    		driver = new EdgeDriver();
    	}
        driver.manage().window().maximize();
        driver.get("https://yalanuwu.github.io/Health-Index-Calculator/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

      
        mainPage = new MainPage(driver);
    }
    

    @AfterTest
    public void closing() {
        //driver.quit();
        System.out.println("Test completed and browser closed.");
    }
=======
	@Test
	public void Hello()
	{
		System.out.println("Hello Team");
		System.out.println("Bilal request");
		System.out.println("deva request");
		
		
		
		}
}


