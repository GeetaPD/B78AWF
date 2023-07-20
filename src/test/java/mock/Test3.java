package mock;

import java.io.FileInputStream;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test3 {

	public static void main(String[] args) throws Exception {
		
		FileInputStream fis = new FileInputStream("./data/Book1.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		
		String un = wb.getSheet("login").getRow(2).getCell(0).toString();
		String pwd = wb.getSheet("login").getRow(2).getCell(1).toString();
		String title = wb.getSheet("login").getRow(2).getCell(2).toString();
		
		WebDriver driver = new EdgeDriver();
		driver.get("https://demo.actitime.com/login.do");
		driver.manage().window().maximize();
		
		driver.findElement(By.id("username")).sendKeys(un);
		Thread.sleep(1000);
		driver.findElement(By.name("pwd")).sendKeys(pwd);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[text()='Login ']")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.titleContains(title));
			System.out.println("Home page is displayed");
		}catch(Exception e) {
			System.out.println("Home page is not displayed");
		}
wb.close();
	}

}
