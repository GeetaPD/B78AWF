package mock;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test4 {
	public static void newTabOpens(WebDriver driver,int tab_no) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
		//Store the ID of the original window
//		String originalWindow = driver.getWindowHandle();

		//Check we don't have other windows open already
		assert driver.getWindowHandles().size() == 1;

		//Wait for the new window or tab
		wait.until(ExpectedConditions.numberOfWindowsToBe(tab_no));

		//Loop through until we find a new window handle
		for (String windowHandle : driver.getWindowHandles()) {
//		    if(!originalWindow.contentEquals(windowHandle)) {
		        driver.switchTo().window(windowHandle);
//		        break;
//		    }
		}

		String title = driver.getTitle();
		//Wait for the new tab to finish loading content
		wait.until(ExpectedConditions.titleContains(title));
	}
	
	

	public static void main(String[] args) throws Exception {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		
		//--------------------------Item 1-----------------------------------
		
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Kurtis");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()=' set for women latest design']")).click();
		
		Actions action = new Actions(driver);
		WebElement kurti = driver.findElement(By.xpath("(//span[text()='ANNI DESIGNER'])[11]"));
		action.scrollToElement(kurti).perform();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//span[contains(text(),'(Itta STY)')]")).click();
		
		newTabOpens(driver,2);
		
		action.scrollByAmount(0,700).perform();
		WebElement listbox = driver.findElement(By.id("native_dropdown_selected_size_name"));
		Select select = new Select(listbox);
		select.selectByVisibleText(" M ");
			
		Thread.sleep(1000);
		action.scrollByAmount(0,-400).perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
		
		
		//--------------------------Item 2 ---------------------------------
		Thread.sleep(3000);
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("purse");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='s for women stylish latest']")).click();
		
		WebElement purse = driver.findElement(By.xpath("(//span[text()='Lavie'])[2]"));
		action.scrollToElement(purse).perform();
		Thread.sleep(6000);
		driver.findElement(By.xpath("(//span[contains(text(),'Detachable Chain Sling Strap')])[3]")).click();
		
		newTabOpens(driver,3);
		
		action.scrollByAmount(0,500).perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
		
		//----------------------------Item 3----------------------------------
		Thread.sleep(3000);
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("jhumka");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='s for women stylish']")).click();
		
		WebElement jhumka = driver.findElement(By.xpath("(//span[text()='Yellow Chimes'])[5]"));
		action.scrollToElement(jhumka).perform();
		Thread.sleep(4000);
		driver.findElement(By.xpath("(//span[contains(text(),'Traditional Multicolor Meenakari Jhumka')])[1]")).click();
		
		newTabOpens(driver,4);
		
		action.scrollByAmount(0,500).perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
		
		
		//Click on cart
		Thread.sleep(1000);
		driver.findElement(By.id("nav-cart-count")).click();
		
		//Store prices in xlsx sheet
		FileInputStream fis = new FileInputStream("./data/Book1.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		
		
		List<WebElement> prices = driver.findElements(By.xpath("//div[@class='sc-badge-price-to-pay']"));
		int count = prices.size();
		int i=1;
//		System.out.println(count); //3
		
		for(WebElement price: prices) {
			String text = price.getText();
			wb.getSheet("Cart").getRow(i).getCell(1).setCellValue(text);
			wb.write(new FileOutputStream("./data/Book1.xlsx"));
			i++;
			if(i==count+1) {
				WebElement totalprice = driver.findElement(By.id("sc-subtotal-amount-activecart"));
				System.out.println(totalprice.getText());
				String totaltext = totalprice.getText();
				wb.getSheet("Cart").getRow(i).getCell(1).setCellValue(totaltext);
				wb.write(new FileOutputStream("./data/Book1.xlsx"));
			}
		}
		
		wb.close();
	}

}
