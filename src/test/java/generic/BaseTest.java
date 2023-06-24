package generic;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {
	public final String DEFAULT_URL="http://www.fb.com";
	public final String DEFAULT_BROWSER="edge";
	public final String DEFAULT_ITO="6";
	public final String DEFAULT_ETO="7";
	public final String XL_PATH="./data/input.xlsx";
	public WebDriver driver;
	public WebDriverWait wait;
	
	@Parameters({"appURL","browser","ITO","ETO"})
	@BeforeMethod
	public void preCondition(@Optional(DEFAULT_URL) String appURL, @Optional(DEFAULT_BROWSER) String browser, @Optional(DEFAULT_ITO) String ITO,@Optional(DEFAULT_ETO) String ETO)
	{
		long longITO=Long.parseLong(ITO);
		long longETO=Long.parseLong(ETO);
		
		if(browser.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else
		{
			driver=new EdgeDriver();
		}
		
		driver.get(appURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(longITO));
		wait=new WebDriverWait(driver, Duration.ofSeconds(longETO));
	}
	
	
	@AfterMethod
	public void postCondition()
	{
		driver.quit();
	}
}
