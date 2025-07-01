package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {
	protected WebDriver driver;
	static String baseUrl;
	
	@BeforeTest
	@Parameters("browser")
	public void setUp(@Optional("chrome")String browser) throws FileNotFoundException, IOException
	{
		if(browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("edge"))
		{
			driver = new EdgeDriver();
		}
		Properties prop = new Properties();
		prop.load(new FileInputStream(System.getProperty("user.dir") + "/src/main/java/utils/Properties.properties"));
		baseUrl = prop.getProperty("baseUrl"); // fetch the URL string
		driver.get(baseUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	
	
	@AfterTest
	public void tearDown()
	{
		if(driver!=null) {
		driver.quit();
		}
	}

}
