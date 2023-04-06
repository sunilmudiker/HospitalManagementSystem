package practicePackage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ClearTrip {
	public static void m1() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.cleartrip.com/flights?utm_source=google&utm_medium=cpc&utm_campaign=BR_Cleartrip-Desktab&dxid=Cj0KCQjw2cWgBhDYARIsALggUhpNb4YroSJ7045jELFjoW58qn_T_cw9u5wkarFDS1dHV7qtDJQX6CYaApbPEALw_wcB&gclid=Cj0KCQjw2cWgBhDYARIsALggUhpNb4YroSJ7045jELFjoW58qn_T_cw9u5wkarFDS1dHV7qtDJQX6CYaApbPEALw_wcB");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement e=driver.findElement(By.xpath("//div[@class='px-1   flex flex-middle nmx-1 pb-1']"));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='px-1   flex flex-middle nmx-1 pb-1']")));
		e.click();
		
		driver.findElement(By.xpath("//div[@class='fs-4 fw-500 c-inherit flex flex-nowrap ml-4']")).click();
		js.executeScript("window.scrollBy(0,300)");
		String month = "Mar"; int year=2024, day= 10;
		boolean flag = false;
		WebElement next = driver.findElement(By.xpath("//div[@class='flex-1 ta-right']/*[name()='svg' and @class='c-pointer']"));
		 do{
		String monthday = driver.findElement(By.xpath("//div[@class='DayPicker-Caption']")).getText();
		String mond = driver.findElement(By.xpath("(//div[@class='DayPicker-Caption'])[2]")).getText();
		if(monthday.equalsIgnoreCase(month+" "+year)||mond.equalsIgnoreCase(month+" "+year)) {
			List<WebElement> lists = driver.findElements(By.xpath("//div[contains(@class,'DayPicker-Day') and @aria-disabled='false']"));
			for(int i=0; i<lists.size();i++) {
				String mony = lists.get(i).getAttribute("aria-label");
				if(mony.contains(month.substring(0, 3)+" "+day+" "+year)) {
					lists.get(i).click();
					flag = true;
					break;
				}
			}
		}
		else if(next.isDisplayed())
			next.click();
		if(flag==true)
			break;
		}while(true);
		//this method takes hell lot of time.
//		while(true) {
//			try {
//				driver.findElement(By.xpath("//div[contains(@class,'DayPicker-Day') and @aria-disabled='false' and contains(@aria-label,'"+month+" "+day+" "+year+"')]")).click();
//				break;
//			}
//			catch(Exception e1) {
//				next.click();
//			}
//		}
		
	}
	
	public static void main(String[] args) {
		m1();
	}
}
